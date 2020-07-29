DROP TABLE user_roles IF EXISTS;

DROP TABLE votes IF EXISTS;
DROP TABLE dishes IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE votes IF EXISTS;
DROP TABLE restaurants IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 10000;

CREATE TABLE users
(
    id         INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE  NOT NULL
    /*voted      BOOLEAN   DEFAULT FALSE NOT NULL*/
    --   voting_time   TIMESTAMP,
    --   restaurant_id INTEGER,
    --   FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
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
    id   INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name VARCHAR(255) NOT NULL
    /* FOREIGN KEY (menu_id) REFERENCES menus (id) ON DELETE CASCADE*/
);
CREATE UNIQUE INDEX restaurants_unique_name_idx
    ON RESTAURANTS (name);

CREATE TABLE dishes
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name          VARCHAR(255)            NOT NULL,
    price         DOUBLE                  NOT NULL,
    added         TIMESTAMP DEFAULT now(),
    isIncludedInMenu      BOOLEAN   DEFAULT TRUE,
    restaurant_id INTEGER,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) on delete set null
);

CREATE TABLE votes
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    added         TIMESTAMP NOT NULL,
    restaurant_id INTEGER   NOT NULL,
    user_id       INTEGER   NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) /*ON DELETE CASCADE*/,
    FOREIGN KEY (user_id) REFERENCES USERS (id) /*ON DELETE CASCADE*/
);
