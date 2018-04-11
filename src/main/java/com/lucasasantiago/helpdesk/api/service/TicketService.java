package com.lucasasantiago.helpdesk.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.lucasasantiago.helpdesk.api.entity.ChangeStatusEntity;
import com.lucasasantiago.helpdesk.api.entity.TicketEntity;

@Component
public interface TicketService {

	TicketEntity createOrUpdate(TicketEntity ticket);

	TicketEntity findById(String id);

	void delete(String id);

	Page<TicketEntity> listTicket(int page, int count);

	ChangeStatusEntity createChangeStatus(ChangeStatusEntity changeStatus);

	Iterable<ChangeStatusEntity> listChangeStatus(String ticketId);

	Page<TicketEntity> findByCurrentUser(int page, int count, String userId);

	Page<TicketEntity> findByParameters(int page, int count, String title, String status, String priority);

	Page<TicketEntity> findByParametersAndCurrentUser(int page, int count, String title, String status, String priority,
			String userId);
	
	Page<TicketEntity> findByNumber(int page, int count, Integer number);
	
	Iterable<TicketEntity> findAll();
	
	Page<TicketEntity> findByParameterAndAssignedUser(int page, int count, String title, String status, String priority, String assignedUserId);
}
