DROP table IF EXISTS APPOINTMENT;
DROP table IF EXISTS USERS;

CREATE TABLE USERS
(
 "ID" NUMBER,
 "ADMIN" VARCHAR(255) NOT NULL,
 "EMAIL_ID" VARCHAR(255) NOT NULL,
 "FIRST_NAME" VARCHAR(255) NOT NULL,
 "LAST_NAME" VARCHAR(255) NOT NULL,
 "MIDDLE_NAME" VARCHAR(255),
 "PHONE" VARCHAR(255),
 "PWD" VARCHAR(255) NOT NULL,
 "USERNAME" VARCHAR(255) NOT NULL,
 "VENDOR" VARCHAR(255) NOT NULL
);

CREATE TABLE APPOINTMENT
(
 "ID" NUMBER,
 "SCHEDULED" VARCHAR(255) NOT NULL,
 "DESCRIPTION" VARCHAR(255) NOT NULL,
 "PASSED" VARCHAR(255) NOT NULL,
 "USERS_ID" NUMBER
);