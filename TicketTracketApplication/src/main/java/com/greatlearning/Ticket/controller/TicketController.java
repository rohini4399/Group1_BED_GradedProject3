package com.greatlearning.Ticket.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.Ticket.entity.Ticket;
import com.greatlearning.Ticket.service.TicketService;

@Controller
@RequestMapping("/")
public class TicketController {

	private TicketService ticketService;

	@Autowired
	public TicketController(TicketService ticketService) {
		super();
		this.ticketService = ticketService;
	}

	@GetMapping("/ticket")
	public String listTickets(Model theModel) {
		List<Ticket> theTicket = ticketService.findAllTickets();
		theModel.addAttribute("ticket", theTicket);
		return "ticket";
	}
	
	@GetMapping("/search")
    public List<Ticket> searchTickets(@RequestParam(value = "keyword", defaultValue = "") String keyword) {
        return ticketService.searchTickets(keyword);
    }

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Ticket theTicket = new Ticket();
		LocalDate currentDate = LocalDate.now();
		theModel.addAttribute("createdOn", currentDate);
		theModel.addAttribute("ticket", theTicket);
		return "create_ticket";
	}

	@PostMapping("/ticket/{id}")
	public String updateticket(@PathVariable Long id, @ModelAttribute("ticket") Ticket ticket, Model model) {

		// get ticket from database by id
		Ticket existingTicket = ticketService.findById(id);
		existingTicket.setId(id);
		existingTicket.setTitle(ticket.getTitle());
		existingTicket.setShortDescription(ticket.getShortDescription());
		existingTicket.setContent(ticket.getContent());

		// save updated ticket object
		ticketService.updateTicket(existingTicket);
		return "redirect:/ticket";
	}

	@PostMapping("/ticket")
	public String save(@ModelAttribute("ticket") Ticket theTicket) {
		ticketService.save(theTicket);
		return "redirect:/ticket";
	}

	@GetMapping("/ticket/edit/{id}")
	public String editticketForm(@PathVariable Long id, Model model) {
		model.addAttribute("ticket", ticketService.findById(id));
		return "edit_ticket";
	}

	@GetMapping("/ticket/view/{id}")
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("ticket", ticketService.findById(id));
		return "view_ticket";
	}

	@GetMapping("/ticket/{id}")
	public String delete(@PathVariable Long id) {
		ticketService.deleteTicketById(id);
		return "redirect:/ticket";
	}

}
