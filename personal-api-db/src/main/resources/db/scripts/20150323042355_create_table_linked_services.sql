-- // create table linked_services
-- Migration SQL that makes the change goes here.

CREATE TABLE linked_services (
	id                      BIGINT NOT NULL AUTO_INCREMENT,
	date_created            DATETIME,
	date_friends_downloaded DATETIME,
	date_linked             DATETIME,
	date_login              DATETIME,
	date_modified           DATETIME,
	oauth_token             VARCHAR(255),
	service_id              VARCHAR(255),
	type                    VARCHAR(255),
	account_id              BIGINT,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

ALTER TABLE linked_services
ADD INDEX fk_linked_services_accounts (account_id),
ADD CONSTRAINT fk_linked_services_accounts
FOREIGN KEY (account_id)
REFERENCES accounts (id);


-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE linked_services;

