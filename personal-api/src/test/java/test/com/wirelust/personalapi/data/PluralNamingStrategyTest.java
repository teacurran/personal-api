package test.com.wirelust.personalapi.data;

import com.wirelust.personalapi.data.jpa.PluralNamingStrategy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Date: 23-Apr-2016
 *
 * @author T. Curran
 */
@RunWith(MockitoJUnitRunner.class)
public class PluralNamingStrategyTest {

	@InjectMocks
	PluralNamingStrategy namingStrategy;

	@Test
	public void shouldBeAbleToSetColumnName() {
		// should add tick marks around column
		Assert.assertEquals("column", namingStrategy.columnName("column"));

		// should convert camel case to snake case and add ticks
		Assert.assertEquals("column_name", namingStrategy.columnName("columnName"));

		// should not add ticks if the column name already has them
		Assert.assertEquals("`column_name`", namingStrategy.columnName("`columnName`"));
	}

	@Test
	public void shouldBeAbleToConvertPropertyToColumnName() {
		// should convert camel case to snake case and add ticks
		Assert.assertEquals("column_name", namingStrategy.propertyToColumnName("columnName"));
	}

	@Test
	public void shouldBeAbleToSetJoinColumnName() {
		// should convert camel case to snake case and add ticks
		Assert.assertEquals("column_name", namingStrategy.joinKeyColumnName("columnName", "table"));
	}

	@Test
	public void shouldBeAbleToSetForeignKeyColumnName() {

		Assert.assertEquals("foreign_id", namingStrategy.foreignKeyColumnName(
			"com.scholastic.test.Foreign",
			"foreignObject",
			null,
			"id"));

		Assert.assertEquals("foreign_id", namingStrategy.foreignKeyColumnName(
			null,
			"foreignObject",
			"foreign",
			"id"));
	}

	@Test
	public void shouldBeAbleToNameClass() {
		Assert.assertEquals("users", namingStrategy.classToTableName("User"));
		Assert.assertEquals("user_profiles", namingStrategy.classToTableName("UserProfile"));
	}
}
