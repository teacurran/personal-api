package com.wirelust.personalapi.data.model;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
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

	@ManyToOne
	ActivityType activityType;

	String notes;

	@Temporal(TemporalType.TIMESTAMP)
	Date created;

    @Temporal(TemporalType.TIMESTAMP)
	Date modified;

	@Temporal(TemporalType.TIMESTAMP)
	Date start;

    @Temporal(TemporalType.TIMESTAMP)
	Date completed;

	@PrePersist
	@PreUpdate
	public void updateTimestamps() {
		if (created == null) {
			setCreated(new Date());
		}
		setModified(new Date());
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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getCompleted() {
		return completed;
	}

	public void setCompleted(Date completed) {
		this.completed = completed;
	}
}
