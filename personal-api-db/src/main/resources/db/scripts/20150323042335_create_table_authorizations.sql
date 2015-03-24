-- // create table authorizations
-- Migration SQL that makes the change goes here.

CREATE TABLE authorizations (
	uuid               VARCHAR(45) NOT NULL UNIQUE,
	date_accessed      DATETIME,
	date_created       DATETIME,
	refresh_token      VARCHAR(255),
	request_code       VARCHAR(255),
	token              VARCHAR(255),
	account_id         BIGINT,
	api_applicaiton_id VARCHAR(45),
	PRIMARY KEY (uuid)
);

CREATE UNIQUE INDEX ix_authorizations_token ON authorizations (token);
CREATE UNIQUE INDEX ix_authorizations_refresh_token ON authorizations (refresh_token);
CREATE UNIQUE INDEX ix_authorizations_request_code ON authorizations (request_code);

ALTER TABLE authorizations
ADD INDEX fk_authorizations_accounts (account_id),
ADD CONSTRAINT fk_authorizations_accounts
FOREIGN KEY (account_id)
REFERENCES accounts (id);

ALTER TABLE authorizations
ADD INDEX fk_authorizations_api_applications (api_applicaiton_id),
ADD CONSTRAINT fk_authorizations_api_applications
FOREIGN KEY (api_applicaiton_id)
REFERENCES api_applications (uuid);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE authorizations;
