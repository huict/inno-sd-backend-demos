drop table if exists rankings;
drop table if exists import_rankings;
drop table if exists import_rankings_atp;
drop table if exists import_rankings_wta;
drop table if exists invalid_rankings;

create table rankings(
                         ranking_date varchar(30),
                         rank int,
                         player_id bigint,
                         points int,
                         tour varchar(3),
                         primary key(ranking_date, player_id, tour),
                         foreign key(player_id) references players(player_id)
);

create table invalid_rankings(
                         ranking_date varchar(30),
                         rank int,
                         player_id bigint,
                         points int,
                         tour varchar(3),
                     	 reason varchar(100)
);


create table import_rankings_atp(
                                    ranking_date varchar(30),
                                    rank int,
                                    player_id bigint,
                                    points int
);
create table import_rankings_wta(
                                    ranking_date varchar(30),
                                    rank int,
                                    player_id bigint,
                                    points int
);

do $$
    DECLARE cmd varchar;

    begin
        for cnt in 0..9 loop
                cmd := format('cut -d '','' -f 1,2,3,4 /raw/tennis_atp/atp_rankings_%s0s.csv', cnt);
                begin
                    execute format('
	COPY import_rankings_atp
	FROM program %L
	DELIMITER '',''
	CSV HEADER;', cmd);
                exception when others then continue;
                end;

            end loop;
    end; $$;
COPY import_rankings_atp
    FROM program 'cut -d '','' -f 1,2,3,4 /raw/tennis_atp/atp_rankings_current.csv'
    DELIMITER ','
    CSV HEADER;
	

with invalids as
         (select r.player_id from import_rankings_atp r
				left join players p on p.player_id=r.player_id 
	          	where p.player_id is null)
insert into invalid_rankings
select ratp.*, 'atp', 'missing player' from import_rankings_atp ratp
                     join invalids i on i.player_id=ratp.player_id;

delete from import_rankings_atp ratp
    using invalid_rankings i
where ratp.player_id = i.player_id;


insert into rankings( ranking_date,
                      rank,
                      player_id,
                      points,
                      tour)
select distinct ranking_date,
                min(rank),
                player_id,
                max(points),'atp' as tour from import_rankings_atp
group by ranking_date, player_id, tour;


do $$
    DECLARE cmd varchar;

    begin
        for cnt in 0..9 loop
                cmd := format('cut -d '','' -f 1,2,3,4 /raw/tennis_wta/wta_rankings_%s0s.csv', cnt);
                begin
                    execute format('
	COPY import_rankings_wta
	FROM program %L
	DELIMITER '',''
	CSV HEADER;', cmd);
                exception when others then continue ;
                end;
            end loop;
    end; $$;

COPY import_rankings_wta
    FROM program 'cut -d '','' -f 1,2,3,4 /raw/tennis_wta/wta_rankings_current.csv'
    DELIMITER ','
    CSV HEADER;
	
with invalids as
         (select r.player_id from import_rankings_wta r
				left join players p on p.player_id=r.player_id 
	          	where p.player_id is null)
insert into invalid_rankings
select rwta.*, 'wta', 'missing player' from import_rankings_wta rwta
                     join invalids i on i.player_id=rwta.player_id;

delete from import_rankings_atp rwta
    using invalid_rankings i
where rwta.player_id = i.player_id;



insert into rankings( ranking_date,
                      rank,
                      player_id,
                      points,
                      tour)
select distinct ranking_date,
                min(rank),
                player_id + 300000,
                max(points),'wta' as tour from import_rankings_wta
group by ranking_date, player_id, tour;

