-- // create table accounts
-- Migration SQL that makes the change goes here.

CREATE TABLE accounts (
	id                  BIGINT  NOT NULL AUTO_INCREMENT,
	admin               BOOLEAN NOT NULL,
	avatar              VARCHAR(255),
	background          VARCHAR(255),
	bio                 LONGTEXT,
	date_created        DATETIME,
	date_login          DATETIME,
	date_modified       DATETIME,
	disabled            BOOLEAN NOT NULL,
	disabled_reason     VARCHAR(255),
	email               VARCHAR(255),
	followers_count     INTEGER,
	following_count     INTEGER,
	full_name           VARCHAR(200),
	location            VARCHAR(255),
	login_failure_count INTEGER,
	password            VARCHAR(255),
	password_salt       VARCHAR(255),
	public_video_count  INTEGER,
	timezone            INTEGER,
	total_video_count   INTEGER,
	username            VARCHAR(255) UNIQUE,
	username_normalized VARCHAR(255) UNIQUE,
	website             VARCHAR(255),
	PRIMARY KEY (id)
);

CREATE TABLE account_password_resets (
	uuid         VARCHAR(45) NOT NULL UNIQUE,
	date_created DATETIME,
	account_id   BIGINT,
	PRIMARY KEY (uuid)
);

ALTER TABLE account_password_resets
ADD INDEX fk_account_password_resets_accounts (account_id),
ADD CONSTRAINT fk_account_password_resets_accounts
FOREIGN KEY (account_id)
REFERENCES accounts (id);

CREATE TABLE account_settings (
	id         BIGINT NOT NULL AUTO_INCREMENT,
	date_set   DATETIME,
	`key`      VARCHAR(255),
	value      VARCHAR(255),
	account_id BIGINT,
	PRIMARY KEY (id)
);

ALTER TABLE account_settings
ADD INDEX fk_account_settings_accounts (account_id),
ADD CONSTRAINT fk_account_settings_accounts
FOREIGN KEY (account_id)
REFERENCES accounts (id);

CREATE TABLE login_audits (
	uuid          VARCHAR(45) NOT NULL UNIQUE,
	date_created  DATETIME,
	ip_address    VARCHAR(255),
	is_successful BOOLEAN,
	username      VARCHAR(255),
	account_id    BIGINT,
	PRIMARY KEY (uuid)
);

ALTER TABLE login_audits
ADD INDEX fk_login_audits_accounts (account_id),
ADD CONSTRAINT fk_login_audits_accounts
FOREIGN KEY (account_id)
REFERENCES accounts (id);

CREATE TABLE restricted_usernames (
	id                  BIGINT NOT NULL AUTO_INCREMENT,
	date_created        DATETIME,
	date_modified       DATETIME,
	reason              VARCHAR(255),
	username            VARCHAR(255),
	username_normalized VARCHAR(255),
	PRIMARY KEY (id)
);



-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE restricted_usernames;
DROP TABLE login_audits;
DROP TABLE account_settings;
DROP TABLE accounts;

