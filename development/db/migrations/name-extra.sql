alter table person
    add given_name varchar(255);

alter table person
    add family_name varchar(255);

update person
    set given_name = first_name
    where person.given_name is null;

update person
    set family_name = last_name
    where person.family_name is null;