package com.greatlearning.Ticket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.Ticket.dao.TicketRepository;
import com.greatlearning.Ticket.entity.Ticket;

@Service
public class TicketServiceImpl implements TicketService {

	private TicketRepository ticketRepository;

	// types of injection methods in spring- by field, by constructor, by setter

	@Autowired // constructor injection
	public TicketServiceImpl(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	@Override
	public List<Ticket> findAllTickets() {
		List<Ticket> theTickets = ticketRepository.findAll();
		return theTickets;
	}
	
	public List<Ticket> searchTickets(String keyword) {
        return ticketRepository.searchTickets(keyword);
    }
	
	@Override
	public Ticket findById(Long theId) {
		Optional<Ticket> result = ticketRepository.findById(theId);

		Ticket theTicket = null;
		if (result.isPresent()) {
			theTicket = result.get();
		} else
			throw new RuntimeException("Did not find book id- " + theId);
		return theTicket;
	}

	@Override
	public void save(Ticket theTicket) {
		ticketRepository.save(theTicket);
	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Override
	public void deleteTicketById(Long theId) {
		ticketRepository.deleteById(theId);
	}

	@Override
	public Ticket findTicketByUrl(String ticketUrl) {
		// TODO Auto-generated method stub
		return null;
	}
}
