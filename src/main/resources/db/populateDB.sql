DELETE
FROM user_roles;
DELETE
FROM votes;
DELETE
FROM meals;
DELETE
FROM users;
DELETE
FROM restaurants;
DELETE
FROM votes;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001),
       ('ROLE_USER', 100001);

INSERT INTO restaurants(name)
VALUES ('SteakHouse'),
       ('Italian restaurant'),
       ('Vietnam restaurant');

INSERT INTO meals (name, price, added, restaurant_id)
        VALUES ('Daily pizza', 500.00, '2020-03-30 10:00:00', 100002),
               ('Roasted salmon', 700.00, '2020-03-30 10:00:00', 100002),
               ('Grilled shrimps', 600.00, '2020-03-30 10:00:00', 100004),
               ('Ribeye steak', 1000.00, '2020-03-30 10:00:00', 100003),
               ('Machete steak', 700.00, '2020-03-30 10:00:00', 100003),
               ('Pho bo soup', 400.00, '2020-03-30 10:00:00', 100004),
               ('Chicken Parmigiana', 400.00, '2020-03-31 10:00:00', 100002),
               ('Fried Mozzarella', 750.50, '2020-03-31 10:00:00', 100002),
               ('Summer Rolls', 600.00, '2020-03-31 10:00:00', 100004),
               ('T-bone', 1000.50, '2020-03-31 10:00:00', 100003),
               ('Lamb Chops', 700.50, '2020-03-31 10:00:00', 100003),
               ('Crispy Baby Squid', 400.50, '2020-03-31 10:00:00', 100004);

INSERT INTO votes (added, restaurant_id, user_id)
VALUES ('2015-05-31 10:00:00', 100003, 100000),
       ('2015-05-31 14:00:00', 100002, 100001),
       ('2015-05-31 10:00:00', 100003, 100001);
