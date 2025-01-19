DROP TABLE IF EXISTS Users;

CREATE TABLE IF NOT EXISTS Users (
    id BIGSERIAL primary key,
    name varchar(100) NOT NULL,
    email varchar(100) DEFAULT NULL,
    created_on TIMESTAMP,
    updated_on TIMESTAMP,
    version INT NOT NULL DEFAULT 0 -- Version column
);

DROP TABLE IF EXISTS Post;

CREATE TABLE Post (
    id varchar(255) NOT NULL,
    title varchar(255) NOT NULL,
    slug varchar(255) NOT NULL,
--     content TEXT,        -- Content of the post (can be longer text)
--     published_on TIMESTAMP WITH TIME ZONE, -- Date and time the post was published
--     updated_on TIMESTAMP WITH TIME ZONE,  -- Date and time the post was last updated
    date date NOT NULL,
    time_to_read int NOT NULL,
    tags varchar(255),
    version INT,
    PRIMARY KEY (id)
);