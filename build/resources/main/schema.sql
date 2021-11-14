DROP TABLE IF EXISTS user_roles;

DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS restaurants;

CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
    ON USERS (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE dishes
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    price            DOUBLE PRECISION        NOT NULL,
    added            TIMESTAMP DEFAULT now() NOT NULL,
    isIncludedInMenu BOOLEAN   DEFAULT TRUE  NOT NULL,
    restaurant_id    INTEGER                 NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
    id            SERIAL PRIMARY KEY,
    added         DATE DEFAULT now(),
    restaurant_id INTEGER NOT NULL,
    user_id       INTEGER NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX votes_unique_user_added_idx
    ON votes (user_id, added)
