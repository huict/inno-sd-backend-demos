drop table if exists points;

create table points
(
    id                       serial primary key,
    match_singles_tourney_id varchar(50) not null,
    match_singles_match_num  int not null,
    match_singles_tour       varchar(3) not null,
    player_id                bigint not null,
    kmh                      int not null,
    elapsed_time              varchar(20) not null,
    foreign key (player_id) references players (player_id),
    foreign key (match_singles_tourney_id, match_singles_match_num, match_singles_tour) references matches_singles(tourney_id, match_num, tour)
);

alter table players add version int default 0;
