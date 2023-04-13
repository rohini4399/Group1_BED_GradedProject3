package com.greatlearning.Ticket.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greatlearning.Ticket.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	@Query("SELECT t FROM Ticket t WHERE t.title LIKE %:keyword% OR t.shortDescription LIKE %:keyword%")
	List<Ticket> searchTickets(@Param("keyword") String keyword);

}
