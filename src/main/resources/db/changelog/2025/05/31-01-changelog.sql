-- liquibase formatted sql

-- changeset Danya:1748686986385-1
CREATE SEQUENCE IF NOT EXISTS joke_call_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Danya:1748686986385-2
CREATE TABLE joke_call
(
    id        BIGINT NOT NULL,
    joke_id   BIGINT,
    user_id   BIGINT,
    call_time TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_joke_call PRIMARY KEY (id)
);

