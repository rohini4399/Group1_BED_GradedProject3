package com.greatlearning.Ticket.service;

import java.util.List;

import com.greatlearning.Ticket.entity.Ticket;

public interface TicketService {

	public List<Ticket> findAllTickets();
	
	List<Ticket> searchTickets(String query);
	
	Ticket findTicketByUrl(String ticketUrl);
	
	public Ticket findById(Long theId);
	
	public void save(Ticket theTicket);
	
	public void deleteTicketById(Long theId);

	public Ticket updateTicket(Ticket ticket);
	 
}
