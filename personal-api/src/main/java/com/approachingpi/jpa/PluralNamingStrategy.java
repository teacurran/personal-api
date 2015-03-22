package com.approachingpi.jpa;

import org.hibernate.cfg.ImprovedNamingStrategy;

import org.jvnet.inflector.Noun;

/**
 * Date: May 5, 2010
 *
 * @author T. Curran
 */
public class PluralNamingStrategy extends ImprovedNamingStrategy {

	private static final long serialVersionUID = 4674532118559124654L;

	@Override
	public String foreignKeyColumnName(String propertyName,
			String propertyEntityName, String propertyTableName,
			String referencedColumnName) {
		return columnName(propertyName) + "_id";
	}

	@Override
	public String classToTableName(String className) {
		return toLowerCase(Noun.pluralOf(super.classToTableName(className)));
	}

	// IMPORTANT! To ensure compatibility across windows and unix, convert all table
	// names to lowercase
	private String toLowerCase(String name) {
		return name.toLowerCase();
	}
}