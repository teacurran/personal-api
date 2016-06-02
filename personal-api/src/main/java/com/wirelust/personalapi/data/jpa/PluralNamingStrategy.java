package com.wirelust.personalapi.data.jpa;

import org.hibernate.AssertionFailure;
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
	public String columnName(String columnName) {
		return addUnderscores(columnName);
	}

	@Override
	public String joinKeyColumnName(String joinedColumn, String joinedTable) {
		return columnName( joinedColumn );
	}

	@Override
	public String foreignKeyColumnName(
			final String propertyName,
			final String propertyEntityName,
			final String propertyTableName,
			final String referencedColumnName) {

		final String header = propertyName != null ? this.unqualify(propertyName) : propertyTableName;
		if (header == null) {
			throw new AssertionFailure("NamingStrategy not properly filled");
		}
		return this.columnName(header + "_" + referencedColumnName);
	}

	@Override
	public String classToTableName(
			final String className) {

		final String tableName = super.classToTableName(className);
		if (tableName.lastIndexOf('_') > -1) {
			String toPlural = tableName.substring(tableName.lastIndexOf('_') + 1);
			final String prefix = tableName.substring(0, tableName.lastIndexOf('_'));
			toPlural = Noun.pluralOf(toPlural);
			final String combined = prefix + "_" + toPlural;
			return this.toLowerCase(combined);
		} else {
			return this.toLowerCase(Noun.pluralOf(super.classToTableName(className)));
		}
	}

	// IMPORTANT! To ensure compatibility across windows and unix, convert all table
	// names to lowercase
	private String toLowerCase(String name) {
		return name.toLowerCase();
	}

	/*
	 * Copied from org.hibernate.util.StringHelper Hibernate 4.0 moved String helper to
	 * org.hibernate.internal.util.StringHelper by having the method here, this class will be
	 * compatible with both hibernate 3.5 and 4.0
	 */
	private String unqualify(
			final String qualifiedName) {

		final int loc = qualifiedName.lastIndexOf('.');
		return (loc < 0) ? qualifiedName : qualifiedName.substring(loc + 1);
	}
}
