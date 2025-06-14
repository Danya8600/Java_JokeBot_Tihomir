-- liquibase formatted sql

-- changeset Danya:1749922790598-1
CREATE SEQUENCE IF NOT EXISTS authority_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Danya:1749922790598-3
CREATE SEQUENCE IF NOT EXISTS role_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Danya:1749922790598-4
CREATE SEQUENCE IF NOT EXISTS user_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Danya:1749922790598-5
CREATE TABLE authorities
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_authorities PRIMARY KEY (id)
);

-- changeset Danya:1749922790598-7
CREATE TABLE role_authorities
(
    authority_id BIGINT NOT NULL,
    role_id      BIGINT NOT NULL,
    CONSTRAINT pk_role_authorities PRIMARY KEY (authority_id, role_id)
);

-- changeset Danya:1749922790598-8
CREATE TABLE roles
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

-- changeset Danya:1749922790598-9
CREATE TABLE users
(
    id       BIGINT  NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255),
    expired  BOOLEAN NOT NULL,
    locked   BOOLEAN NOT NULL,
    enabled  BOOLEAN NOT NULL,
    role_id  BIGINT,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

-- changeset Danya:1749922790598-10
ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

-- changeset Danya:1749922790598-11
ALTER TABLE role_authorities
    ADD CONSTRAINT fk_rolaut_on_authority FOREIGN KEY (authority_id) REFERENCES authorities (id);

-- changeset Danya:1749922790598-12
ALTER TABLE role_authorities
    ADD CONSTRAINT fk_rolaut_on_role FOREIGN KEY (role_id) REFERENCES roles (id);

