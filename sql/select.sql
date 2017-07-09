-- show all users where user_name contains 'e'
select user_name
from user_profile
where user_name like '%e%';

--show user_id and amount of requests by this id
select user_id, count(request_id) from request group by user_id;

--show user_id and username and amount of requests by this user
select r.user_id, u.user_name, count(r.request_id)
from request as r inner join user_profile as u
on r.user_id = u.user_id
group by r.user_id, u.user_name;

