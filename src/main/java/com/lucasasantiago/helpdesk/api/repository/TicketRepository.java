package com.lucasasantiago.helpdesk.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.lucasasantiago.helpdesk.api.entity.TicketEntity;

public interface TicketRepository extends MongoRepository<TicketEntity, String> {

	Page<TicketEntity> findByUserIdOrderByDateDesc(Pageable pages, String userId);

	Page<TicketEntity> findByTitleIgnoreCaseContainingAndStatusAndPriorityOrderByDateDesc(
			String title, String status, String priority, Pageable pages);

	Page<TicketEntity> findByTitleIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc(
			String title, String status, String priority, Pageable pages);

	Page<TicketEntity> findByTitleIgnoreCaseContainingAndStatusAndPriorityAndAssignedUserIdOrderByDateDesc(
			String title, String status, String priority, Pageable pages);
	
	Page<TicketEntity> findByNumber(Integer number, Pageable pages);
	
	
}
