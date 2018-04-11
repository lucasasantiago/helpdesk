package com.lucasasantiago.helpdesk.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lucasasantiago.helpdesk.api.entity.ChangeStatusEntity;
import com.lucasasantiago.helpdesk.api.entity.TicketEntity;
import com.lucasasantiago.helpdesk.api.repository.ChangeStatusRepository;
import com.lucasasantiago.helpdesk.api.repository.TicketRepository;
import com.lucasasantiago.helpdesk.api.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private ChangeStatusRepository changeStatusRepository;

	@Override
	public TicketEntity createOrUpdate(TicketEntity ticket) {
		return this.ticketRepository.save(ticket);
	}

	@Override
	public TicketEntity findById(String id) {
		return this.ticketRepository.findById(id).orElseGet(null);
	}

	@Override
	public void delete(String id) {
		this.ticketRepository.deleteById(id);
	}

	@Override
	public Page<TicketEntity> listTicket(int page, int count) {
		return this.ticketRepository.findAll(PageRequest.of(page, count));
	}

	@Override
	public ChangeStatusEntity createChangeStatus(ChangeStatusEntity changeStatus) {
		return this.changeStatusRepository.save(changeStatus);
	}

	@Override
	public Iterable<ChangeStatusEntity> listChangeStatus(String ticketId) {
		return this.changeStatusRepository.findByTicketIdOrderByDateChangeStatusDesc(ticketId);
	}

	@Override
	public Page<TicketEntity> findByCurrentUser(int page, int count, String userId) {
		return this.ticketRepository.findByUserIdOrderByDateDesc(PageRequest.of(page, count), userId);
	}

	@Override
	public Page<TicketEntity> findByParameters(int page, int count, String title, String status, String priority) {
		return this.ticketRepository.findByTitleIgnoreCaseContainingAndStatusAndPriorityOrderByDateDesc(title, status,
				priority, PageRequest.of(page, count));
	}

	@Override
	public Page<TicketEntity> findByParametersAndCurrentUser(int page, int count, String title, String status,
			String priority, String userId) {
		return this.ticketRepository.findByTitleIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc(title,
				status, priority, userId, PageRequest.of(page, count));
	}

	@Override
	public Page<TicketEntity> findByNumber(int page, int count, Integer number) {
		return this.ticketRepository.findByNumber(number, PageRequest.of(page, count));
	}

	@Override
	public Iterable<TicketEntity> findAll() {
		return this.ticketRepository.findAll();
	}

	@Override
	public Page<TicketEntity> findByParameterAndAssignedUser(int page, int count, String title, String status,
			String priority, String assignedUserId) {
		return this.ticketRepository
				.findByTitleIgnoreCaseContainingAndStatusAndPriorityAndAssignedUserIdOrderByDateDesc(title, status,
						priority, assignedUserId, PageRequest.of(page, count));
	}

}
