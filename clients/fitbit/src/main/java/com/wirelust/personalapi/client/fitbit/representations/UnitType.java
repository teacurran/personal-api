package com.wirelust.personalapi.client.fitbit.representations;

/**
 * 1/19/15
 *
 * @Author T. Curran
 */
public class UnitType {

	Long id;
	String name;
	String plural;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlural() {
		return plural;
	}

	public void setPlural(String plural) {
		this.plural = plural;
	}
}
