-- // activities tables
-- Migration SQL that makes the change goes here.

CREATE TABLE activity_types (
	id                      BIGINT NOT NULL AUTO_INCREMENT,
	date_created            DATETIME NOT NULL,
	date_modified           DATETIME NOT NULL,
	name                    VARCHAR(200),
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE activities (
	id                      BIGINT NOT NULL AUTO_INCREMENT,
	activity_type_id        BIGINT,
	date_created            DATETIME NOT NULL,
	date_modified           DATETIME NOT NULL,
	date_started            DATETIME NOT NULL,
	date_completed          DATETIME NULL,
	notes                   TEXT,
	PRIMARY KEY (id),
	KEY `fk_activities_activity_types` (`activity_type_id`),
	CONSTRAINT `fk_activities_activity_types` FOREIGN KEY (`activity_type_id`) REFERENCES `activity_types` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE activities;
DROP TABLE activity_types;
