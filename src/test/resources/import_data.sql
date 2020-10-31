insert into category (name, name_en, name_ua)  values ('Design', 'Design', '??????');
insert into category (name, name_en, name_ua)  values ('Databases', 'Databases','???? ?????');
insert into category (name, name_en, name_ua)  values ('UI/UX', 'UI/UX', 'UI/UX');
insert into category (name, name_en, name_ua)  values ('SMM', 'SMM', '???');
insert into category (name, name_en, name_ua)  values ('Programming', 'Programming', '?????????????');

insert into activity(  name, name_en, name_ua, description,description_en, description_ua, enabled) values ( 'test','test','test','test','test','test',true
);
insert into  activity_category(activity_id, category_id) values (1,1);

insert into user (firstname, login, lastname, password, email, role) values ('testvalue','testvalue','testvalue','testvalue','testvalue', 'USER');

insert into user_activity (created_request_at, status, id_user, id_activity) values ('2020-10-04 17:02:00', 'REQUESTED_ADD', 1 ,1);