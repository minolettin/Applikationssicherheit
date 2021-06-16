DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS authorities;
DROP INDEX IF EXISTS ix_auth_username;

CREATE TABLE users (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled SMALLINT NOT NULL DEFAULT 1,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    PRIMARY KEY (username, authority),
    FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username
  on authorities (username,authority);
