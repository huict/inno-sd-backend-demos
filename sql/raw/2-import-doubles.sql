create table matches_doubles (
                                 tourney_id varchar(50),
                                 tourney_name varchar(50),
                                 tour varchar(3),
                                 surface varchar(10),
                                 tourney_date varchar(12),
                                 match_num int,
                                 winner1_id bigint,
                                 winner2_id bigint,
                                 loser1_id bigint,
                                 loser2_id bigint,
                                 primary key(tourney_id, match_num),
                                 foreign key(winner1_id) references players(player_id),
                                 foreign key(loser1_id) references players(player_id),
                                 foreign key(winner2_id) references players(player_id),
                                 foreign key(loser2_id) references players(player_id)
);

create table import_matches_doubles_atp (
                                            tourney_id varchar(50),
                                            tourney_name varchar(50),
                                            surface varchar(10),
                                            tourney_date varchar(12),
                                            match_num int,
                                            winner1_id bigint,
                                            winner2_id bigint,
                                            loser1_id bigint,
                                            loser2_id bigint
);

create table invalid_matches_doubles (
                                         tourney_id varchar(50),
                                         tourney_name varchar(50),
                                         surface varchar(10),
                                         tourney_date varchar(12),
                                         match_num int,
                                         winner1_id bigint,
                                         winner2_id bigint,
                                         loser1_id bigint,
                                         loser2_id bigint,
                                         reason varchar(100)
);

do $$
DECLARE cmd varchar;

begin
for cnt in 2000..2020 loop
    cmd := format('cut -d '','' -f 1,2,3,6,7,8,9,12,13 /raw/tennis_atp/atp_matches_doubles_%s.csv', cnt);

execute format('
	COPY import_matches_doubles_atp
	FROM program %L
	DELIMITER '',''
	CSV HEADER;', cmd);
end loop;
end; $$;


with invalids as
         (select tourney_id, match_num, count(*) from import_matches_doubles_atp m
          group by tourney_id, match_num
          having count(*) > 1)
insert into invalid_matches_doubles
select mi.*, 'duplicate id' from import_matches_doubles_atp mi
                                     join invalids i on mi.tourney_id = i.tourney_id and mi.match_num = i.match_num;

with invalids as
         (select tourney_id, match_num, winner1_id, winner2_id, loser1_id, loser2_id from import_matches_doubles_atp m
                                                                                              left join players p1 on p1.player_id = winner1_id
                                                                                              left join players p2 on p2.player_id = winner2_id
                                                                                              left join players p3 on p3.player_id = loser1_id
                                                                                              left join players p4 on p4.player_id = loser2_id
          where p1.player_id is null or p2.player_id is null or p3.player_id is null or p4.player_id is null)
insert into invalid_matches_doubles
select mi.*, 'player missing' from import_matches_doubles_atp mi
                                       join invalids i on mi.tourney_id = i.tourney_id and mi.match_num = i.match_num;



delete from import_matches_doubles_atp mi
    using invalid_matches_doubles i
where mi.tourney_id = i.tourney_id and mi.match_num = i.match_num;

insert into matches_doubles(
    tourney_id,	tourney_name,tour,surface, tourney_date, match_num, winner1_id,	winner2_id,	loser1_id,	loser2_id)
select tourney_id,	tourney_name,'atp',surface, tourney_date, match_num, winner1_id,	winner2_id,	loser1_id,	loser2_id
from import_matches_doubles_atp;