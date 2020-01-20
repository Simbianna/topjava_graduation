DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurants;
DELETE FROM votes;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurants(name)
VALUES ('SteakHouse'),
       ('Italian restaurant'),
       ('Vietnam restaurant');

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100003),
       ('ROLE_ADMIN', 100004);
--        ('ROLE_USER', 100003);

INSERT INTO meals (name, price, added, restaurant_id)
VALUES ('Daily pizza', 500, '2015-05-30 10:00:00', 100001),
       ('Roasted salmon', 700, '2015-05-30 10:00:00', 100001),
       ('Grilled shrimps', 600, '2015-05-30 10:00:00', 100002),
       ('Ribeye steak', 1000, '2015-05-31 10:00:00', 100000),
       ('Machete steak', 700, '2015-05-31 10:00:00', 100000),
       ('Pho bo soup', 400, '2015-05-31 10:00:00', 100002);

INSERT INTO votes (added, restaurant_id, user_id)
VALUES ('2015-05-31 10:00:00', 100000, 100003),
       ('2015-05-31 14:00:00', 100000, 100003),
       ('2015-05-31 10:00:00', 100000, 100003);
