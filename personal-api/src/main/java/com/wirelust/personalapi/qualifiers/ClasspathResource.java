package com.wirelust.personalapi.qualifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Date: 09-03-2015
 *
 * @Author T. Curran
 */
@Retention(RUNTIME)
@Target({METHOD, TYPE, FIELD, PARAMETER})
@Documented
@Qualifier
public @interface ClasspathResource {

	@Nonbinding String value();

}