create table import_matches_singles_atp (
                                            tourney_id varchar(50),
                                            tourney_name varchar(50),
                                            surface varchar(10),
                                            tourney_date varchar(12),
                                            match_num int,
                                            winner_id bigint,
                                            loser_id bigint
);

create table matches_singles (
                                 tourney_id varchar(50),
                                 tourney_name varchar(50),
                                 tour varchar(3),
                                 surface varchar(10),
                                 tourney_date varchar(12),
                                 match_num int,
                                 winner_id bigint,
                                 loser_id bigint,
                                 primary key(tourney_id, match_num, tour),
                                 foreign key(winner_id) references players(player_id),
                                 foreign key(loser_id) references players(player_id)
);


create table import_matches_singles_wta (
                                            tourney_id varchar(50),
                                            tourney_name varchar(50),
                                            surface varchar(10),
                                            tourney_date varchar(12),
                                            match_num int,
                                            winner_id bigint,
                                            loser_id bigint
);

create table invalid_matches_singles (
                                         tourney_id varchar(50),
                                         tourney_name varchar(50),
                                         surface varchar(10),
                                         tourney_date varchar(12),
                                         match_num int,
                                         winner_id bigint,
                                         loser_id bigint
);


do $$
DECLARE cmd varchar;

begin
for cnt in 1968..2023 loop
    cmd := format('cut -d '','' -f 1,2,3,6,7,8,16 /raw/tennis_atp/atp_matches_%s.csv', cnt);

execute format('
	COPY import_matches_singles_atp
	FROM program %L
	DELIMITER '',''
	CSV HEADER;', cmd);
end loop;
end; $$

;with invalids as
          (select tourney_id, match_num, count(*) from import_matches_singles_atp m
           group by tourney_id, match_num
           having count(*) > 1)
 insert into invalid_matches_singles
select mi.* from import_matches_singles_atp mi
                     join invalids i on mi.tourney_id = i.tourney_id and mi.match_num = i.match_num;

with invalids as
         (select tourney_id, match_num, winner_id, loser_id from import_matches_singles_atp m
                                                                     left join players p1 on p1.player_id = winner_id
                                                                     left join players p2 on p2.player_id = loser_id
          where p1.player_id is null or p2.player_id is null)
insert into invalid_matches_singles
select mi.* from import_matches_singles_atp mi
                     join invalids i on mi.tourney_id = i.tourney_id and mi.match_num = i.match_num;

delete from import_matches_singles_atp mi
    using invalid_matches_singles i
where mi.tourney_id = i.tourney_id and mi.match_num = i.match_num;


insert into matches_singles(
    tourney_id,	tourney_name,tour,surface, tourney_date, match_num, winner_id, loser_id)
select tourney_id,	tourney_name,'atp',surface, tourney_date, match_num, winner_id,	loser_id
from import_matches_singles_atp;


do $$
DECLARE cmd varchar;

begin
for cnt in 1920..2023 loop
    cmd := format('cut -d '','' -f 1,2,3,6,7,8,16 /raw/tennis_wta/wta_matches_%s.csv', cnt);

execute format('
	COPY import_matches_singles_wta
	FROM program %L
	DELIMITER '',''
	CSV HEADER;', cmd);
end loop;
end; $$

;with invalids as
          (select tourney_id, match_num, count(*) from import_matches_singles_wta m
           group by tourney_id, match_num
           having count(*) > 1)
 insert into invalid_matches_singles
select mi.* from import_matches_singles_wta mi
                     join invalids i on mi.tourney_id = i.tourney_id and mi.match_num = i.match_num;

with invalids as
         (select tourney_id, match_num, winner_id, loser_id from import_matches_singles_wta m
                                                                     left join players p1 on p1.player_id= winner_id+300000
                                                                     left join players p2 on p2.player_id= loser_id+300000
          where p1.player_id is null or p2.player_id is null)
insert into invalid_matches_singles
select mi.* from import_matches_singles_wta mi
                     join invalids i on mi.tourney_id = i.tourney_id and mi.match_num = i.match_num;

delete from import_matches_singles_wta mi
    using invalid_matches_singles i
where mi.tourney_id = i.tourney_id and mi.match_num = i.match_num;


insert into matches_singles(
    tourney_id,	tourney_name,tour,surface, tourney_date, match_num, winner_id, loser_id)
select tourney_id,	tourney_name,'wta',surface, tourney_date, match_num, (winner_id+ 300000), (loser_id+300000)
from import_matches_singles_wta;