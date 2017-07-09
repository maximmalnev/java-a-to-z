create table permission (
	permission_id serial primary key,
	permission_name character varying (100) not null
);

create table role (
	role_id serial primary key,
	role_name character varying (30) not null
);

create table permission_role (
	role_id integer references role(role_id),
	permission_id integer references permission(permission_id)
);

create table user_profile (
	user_id serial primary key,
	role_id integer references role(role_id),
	user_name character varying (30) not null
);

create table state (
	state_id serial primary key,
	state_name character varying (30) not null
);

create table category (
	category_id serial primary key,
	category_name character varying (30) not null
);

create table request (
	request_id serial primary key,
	user_id integer references user_profile(user_id),
	state_id integer references state(state_id),
	category_id integer references category(category_id)
);

create table file (
	file_id serial primary key,
	request_id integer references request(request_id),
	file_url text not null
);

create table comment (
	comment_id serial primary key,
	request_id integer references request(request_id),
	comment text not null
);