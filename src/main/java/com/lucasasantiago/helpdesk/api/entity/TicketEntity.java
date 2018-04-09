package com.lucasasantiago.helpdesk.api.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lucasasantiago.helpdesk.api.enumerator.PriorityEnum;
import com.lucasasantiago.helpdesk.api.enumerator.StatusEnum;

@Document
public class TicketEntity {

	@Id
	private String id;

	@DBRef(lazy = true)
	private UserEntity user;

	private Date date;

	private String title;

	private Integer number;

	private StatusEnum status;

	private PriorityEnum priority;

	@DBRef(lazy = true)
	private UserEntity assignedUser;

	private String image;

	@Transient
	private List<ChangeStatusEntity> changes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public PriorityEnum getPriority() {
		return priority;
	}

	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}

	public UserEntity getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(UserEntity assignedUser) {
		this.assignedUser = assignedUser;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<ChangeStatusEntity> getChanges() {
		return changes;
	}

	public void setChanges(List<ChangeStatusEntity> changes) {
		this.changes = changes;
	}

}
