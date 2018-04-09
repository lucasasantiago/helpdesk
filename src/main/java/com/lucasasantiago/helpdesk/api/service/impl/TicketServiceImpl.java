package com.lucasasantiago.helpdesk.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lucasasantiago.helpdesk.api.entity.ChangeStatusEntity;
import com.lucasasantiago.helpdesk.api.entity.TicketEntity;
import com.lucasasantiago.helpdesk.api.repository.ChangeStatusRepository;
import com.lucasasantiago.helpdesk.api.repository.TicketRepository;
import com.lucasasantiago.helpdesk.api.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService{

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChangeStatusEntity createChangeStatus(ChangeStatusEntity changeStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ChangeStatusEntity> listChangeStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<TicketEntity> findByCurrentUser(int page, int count, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<TicketEntity> findByParameters(int page, int count, String title, String status, String priority) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<TicketEntity> findByParametersAndCurrentUser(int page, int count, String title, String status,
			String priority, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<TicketEntity> findByNumber(int page, int count, Integer number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<TicketEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<TicketEntity> findByParameterAndAssignedUser(int page, int count, String title, String status,
			String priority, String assignedUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
