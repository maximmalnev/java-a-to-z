--create tables
create table engine (
	id serial primary key,
	name varchar(30)
);
create table transmission (
	id serial primary key,
	name varchar(30)
);
create table car_body (
	id serial primary key,
	name varchar (30)
);
create table car_catalog (
	id serial primary key,
	name varchar (30),
	engine_id int references engine(id),
	transmission_id int references transmission(id),
	car_body_id int references car_body(id)
);

--insert values
insert into engine (name)
values ('engine1'), ('engine2'), ('engine3');

insert into transmission (name)
values ('transmission1'), ('transmission2'), ('transmission3');

insert into car_body (name)
values ('body1'), ('body2'), ('body3');

insert into car_catalog (name, engine_id, transmission_id, car_body_id)
values ('car1', 1, 2, 3), ('car2', 3, 1, 2);

--show all cars
select cat.name, e.name, t.name, b.name from car_catalog as cat
inner join engine as e on cat.engine_id = e.id
inner join transmission as t on cat.transmission_id = t.id
inner join car_body as b on cat.car_body_id = b.id;

--show unused engines
select e.name from engine as e
left outer join car_catalog as cat on e.id = cat.engine_id
where cat.id is null;

--show unused transmissions
select t.name from transmission as t
left outer join car_catalog as cat on t.id = cat.transmission_id
where cat.id is null;

--show unused car_bodys
select b.name from car_body as b
left outer join car_catalog as cat on b.id = cat.car_body_id
where cat.id is null;