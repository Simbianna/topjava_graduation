Delete from users where enabled;

INSERT INTO users (id, name, email, password)
VALUES (1, 'User', 'user@yandex.ru', '$2a$10$Ncl/5ufStKs7VszxoOjOw.0uePNJjyy/iOyL9XkTHM59UobY9TOpa'),
       (2, 'Admin', 'admin@gmail.com', '$2a$10$Ncl/5ufStKs7VszxoOjOw.0uePNJjyy/iOyL9XkTHM59UobY9TOpa');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 1),
       ('ROLE_ADMIN', 2),
       ('ROLE_USER', 2);

INSERT INTO restaurants(id, name)
VALUES (1, 'SteakHouse'),
       (2, 'Italian restaurant'),
       (3, 'Vietnam restaurant'),
       (4, 'Empty restaurant');

INSERT INTO votes (id, added, restaurant_id, user_id)
VALUES (1, '2020-08-01', 2, 2),
       (2, '2020-08-01', 2, 1),
       (3, '2020-08-02', 3, 2),
       (4, '2020-08-02', 3, 1),
       (5, '2020-08-03', 1, 2),
       (6, '2020-08-03', 1, 1),
       (7, '2020-08-04', 2, 2),
       (8, '2020-08-04', 2, 1),
       (9, '2020-08-05', 3, 2),
       (10, '2020-08-05', 3, 1),
       (11, '2020-08-18', 1, 2),
       (12, '2020-08-18', 1, 1);

INSERT INTO dishes (id, name, price, added, restaurant_id)
VALUES (1, 'Ribeye steak', 1000.00, '2020-07-30 10:00:00', 1),
       (2, 'Machete steak', 700.00, '2020-07-30 10:00:00', 1),
       (3, 'T-bone', 1000.50, '2020-07-31 10:00:00', 1),
       (4, 'Daily pizza', 500.00, '2020-07-30 10:00:00', 2),
       (5, 'Chicken Parmigiana', 400.00, '2020-07-31 10:00:00', 2),
       (6, 'Fried Mozzarella', 750.50, '2020-07-31 10:00:00', 2),
       (7, 'Summer Rolls', 600.00, '2020-07-31 10:00:00', 3),
       (8, 'Pho bo soup', 400.00, '2020-07-30 10:00:00', 3),
       (9, 'Crispy Baby Squid', 400.50, '2020-07-31 10:00:00', 3);

INSERT INTO dishes (id, name, price, added, isIncludedInMenu, restaurant_id)
VALUES (10, 'Roasted salmon', 700.00, '2020-07-30 00:00:00', false, 2),
       (11, 'Grilled shrimps', 600.00, '2020-07-30 23:59:00', false, 3),
       (12, 'Lamb Chops', 700.50, '2020-07-31 10:00:00', false, 1);


