package com.wirelust.personalapi.data.model;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Date: 09-Jan-2016
 *
 * @author T. Curran
 */
@Entity
@Access(AccessType.FIELD)
@Cacheable
public class ActivityType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(length=200)
	String name;

	@Temporal(TemporalType.DATE)
	Date created;

    @Temporal(TemporalType.DATE)
	Date modified;

	@PrePersist
	@PreUpdate
	public void updateTimestamps() {
		if (created == null) {
			setCreated(new Date());
		}
		setModified(new Date());
	}

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
}
