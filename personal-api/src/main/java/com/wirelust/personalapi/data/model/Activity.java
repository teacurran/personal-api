package com.wirelust.personalapi.data.model;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Access( AccessType.FIELD )
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@ManyToOne
	ActivityType activityType;

	@Lob
	String notes;

	@Temporal(TemporalType.TIMESTAMP)
	Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
	Date dateModified;

	@Temporal(TemporalType.TIMESTAMP)
	Date dateStarted;

    @Temporal(TemporalType.TIMESTAMP)
	Date dateCompleted;

	@PrePersist
	@PreUpdate
	public void updateTimestamps() {
		if (dateCreated == null) {
			setDateCompleted(new Date());
		}
		setDateModified(new Date());
	}

	public ActivityType getActivityType() {
		return activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
