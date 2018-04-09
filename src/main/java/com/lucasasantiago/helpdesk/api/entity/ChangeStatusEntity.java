package com.lucasasantiago.helpdesk.api.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.lucasasantiago.helpdesk.api.enumerator.StatusEnum;

public class ChangeStatusEntity {

	@Id
	private String id;

	@DBRef(lazy = true)
	private TicketEntity ticket;

	@DBRef(lazy = true)
	private UserEntity userChange;

	private Date dateChangeStatus;

	private StatusEnum status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TicketEntity getTicket() {
		return ticket;
	}

	public void setTicket(TicketEntity ticket) {
		this.ticket = ticket;
	}

	public UserEntity getUserChange() {
		return userChange;
	}

	public void setUserChange(UserEntity userChange) {
		this.userChange = userChange;
	}

	public Date getDateChangeStatus() {
		return dateChangeStatus;
	}

	public void setDateChangeStatus(Date dateChangeStatus) {
		this.dateChangeStatus = dateChangeStatus;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
