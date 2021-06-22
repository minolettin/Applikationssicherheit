DROP TABLE IF EXISTS person_authority;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS authority;

CREATE TABLE person(
    id int NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE authority(
    id int NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE person_authority(
    id int NOT NULL UNIQUE,
    person_id int NOT NULL,
    authority_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (person_id) REFERENCES person(id),
    FOREIGN KEY (authority_id) REFERENCES authority(id)
);
