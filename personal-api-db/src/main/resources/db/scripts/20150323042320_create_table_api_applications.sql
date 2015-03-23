-- // create table api_applications
-- Migration SQL that makes the change goes here.

CREATE TABLE api_applications (
	uuid          VARCHAR(45) NOT NULL UNIQUE,
	approved      BOOLEAN,
	callback_uri  VARCHAR(255),
	date_approved DATETIME,
	date_created  DATETIME,
	date_modified DATETIME,
	name          VARCHAR(255),
	secret        VARCHAR(255),
	account_id    BIGINT,
	PRIMARY KEY (uuid)
);

ALTER TABLE api_applications
ADD INDEX fk_api_applications_accounts (account_id),
ADD CONSTRAINT fk_api_applications_accounts
FOREIGN KEY (account_id)
REFERENCES accounts (id);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE api_applications;

