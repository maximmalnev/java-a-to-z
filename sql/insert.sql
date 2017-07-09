insert into permission (permission_name) values
('can create'), ('can edit'), ('can delete');

insert into role (role_name) values
('admin'), ('user');

insert into permission_role values
(1, 1), (1, 2), (1, 3), (2, 1);

insert into user_profile (role_id, user_name) values
(1, 'Alex'), (2, 'Kate'), (2, 'John');

insert into state (state_name) values
('started'), ('closed');

insert into category (category_name) values
('normal'), ('urgent');

insert into request (user_id, state_id, category_id) values
(2, 1, 1), (3, 1, 2), (2, 1, 1);

insert into file (request_id, file_url) values
(1, 'url_one'), (1, 'url_two');

insert into comment (request_id, comment) values
(1, 'comment1'), (2, 'comment2');