DROP DATABASE IF EXISTS dxcassessment;
CREATE DATABASE dxcassessment;
USE dxcassessment;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;

CREATE TABLE users (
    username varchar(48) NOT NULL,
    password char(68) NOT NULL,
    enabled boolean NOT NULL,
    full_name varchar(128) NOT NULL,

    PRIMARY KEY(username)
);

CREATE TABLE authorities (
    username varchar(48) NOT NULL,
    authority varchar(128) NOT NULL,

    UNIQUE KEY authorities_index (username, authority),
    CONSTRAINT authorities_index FOREIGN KEY(username) REFERENCES users(username)
);

INSERT INTO users(username, password, enabled, full_name)
VALUES ("michael", "{bcrypt}$2a$10$j5nhuwVk7yXzQgfmIej2keXYJNDYe3VAarwebl1iEmCmbu39IfI/K", true, "Michael Bay"),
       ("mabel", "{bcrypt}$2a$10$/g1ttjgcBUMRqhlVEUrDVuDhnKNnIAmMB8QCibp.6POz5SwsWbAE.", true, "Mabel Lynn");

INSERT INTO authorities(username, authority)
VALUES ("michael", "ROLE_USER"),
       ("mabel", "ROLE_USER"),
       ("mabel", "ROLE_MANAGER");