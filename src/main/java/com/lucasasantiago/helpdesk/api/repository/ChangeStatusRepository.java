package com.lucasasantiago.helpdesk.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lucasasantiago.helpdesk.api.entity.ChangeStatusEntity;

public interface ChangeStatusRepository extends MongoRepository<ChangeStatusEntity, String> {

	Iterable<ChangeStatusEntity> findByTicketIdOrderByDateChangeStatusDesc(String ticketId);

}
