DELETE
FROM user_roles;
DELETE
FROM menus;
DELETE
FROM votes;
DELETE
FROM dishes;
DELETE
FROM users;
DELETE
FROM restaurants;
DELETE
FROM votes;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001),
       ('ROLE_USER', 100001);

INSERT INTO restaurants(name, MENU_ID)
VALUES ('SteakHouse', 100009),
       ('Italian restaurant', 100010),
       ('Vietnam restaurant', 100011);


INSERT INTO votes (added, restaurant_id, user_id)
VALUES ('2020-03-30 9:00:00', 100002, 100000),
       ('2020-03-30 10:00:00', 100003, 100001),
       ('2020-03-31 9:00:00', 100003, 100000),
       ('2020-03-31 10:00:00', 100003, 100001);

INSERT INTO menus (restaurant_id)
VALUES (100002),
       (100003),
       (100004);

INSERT INTO dishes (name, price, added, menu_id)
VALUES ('Ribeye steak', 1000.00, '2020-03-30 10:00:00', 100009),
       ('Machete steak', 700.00, '2020-03-30 10:00:00', 100009),
       ('T-bone', 1000.50, '2020-03-31 10:00:00', 100009),
       ('Lamb Chops', 700.50, '2020-03-31 10:00:00', 100009),
       ('Chicken Parmigiana', 400.00, '2020-03-31 10:00:00', 100010),
       ('Fried Mozzarella', 750.50, '2020-03-31 10:00:00', 100010),
       ('Summer Rolls', 600.00, '2020-03-31 10:00:00', 100011),
       ('Pho bo soup', 400.00, '2020-03-30 10:00:00', 100011),
       ('Crispy Baby Squid', 400.50, '2020-03-31 10:00:00', 100011);

INSERT INTO dishes (name, price, added)
VALUES ('Daily pizza', 500.00, '2020-03-30 10:00:00'),
       ('Roasted salmon', 700.00, '2020-03-30 00:00:00'),
       ('Grilled shrimps', 600.00, '2020-03-30 23:59:00');


