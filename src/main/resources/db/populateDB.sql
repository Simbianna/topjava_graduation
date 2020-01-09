DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurants(name)
VALUES ('first'),
       ('second'),
       ('third');

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100003),
       ('ROLE_ADMIN', 100004);
--        ('ROLE_USER', 100003);

INSERT INTO meals (name, price, added, restaurant_id)
VALUES ('soup', 100, '2015-05-30', 100000 ),
       ('toast', 200, '2015-05-30', 100001 ),
       ('soup', 300, '2015-05-30', 100002 ),
       ('steak', 400, '2015-05-31', 100000 ),
       ('steak', 500, '2015-05-31', 100001 ),
       ('soup', 600, '2015-05-31', 100002 );
