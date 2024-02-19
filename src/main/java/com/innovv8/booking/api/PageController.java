package com.innovv8.booking.api;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.innovv8.booking.objectify.model.Reservation;
import com.innovv8.booking.service.ServiceFactory;
import com.innovv8.booking.utilities.Constants;
import com.innovv8.booking.utilities.DateUtilities;
import com.innovv8.booking.utilities.DateUtilities.DateFormat;

@Controller
public class PageController {

	private static final Logger log = Logger.getLogger(PageController.class.getName());

	@GetMapping("admin/reservations/delete/{id}")
	public String deleteReservation(Model model, @PathVariable String id,
			@RequestParam(required = true) String isoDate) {
		ServiceFactory.getReservationService().deleteById(id);

		List<Reservation> reservations = ServiceFactory.getReservationService().getByIsoDateOrderByTimeSlot(isoDate);
		model.addAttribute("reservations", reservations);
		model.addAttribute("isoDate", isoDate);
		return "reservations";
	}

	@GetMapping("admin/reservations")
	public String getReservationsPage(Model model, @RequestParam(required = false) String isoDate) {
		if (null == isoDate || isoDate.isEmpty()) {
			isoDate = DateUtilities.formatDate(new Date(), DateFormat.DATE.toString(), Constants.TIMEZONE_ID);
		}
		List<Reservation> reservations = ServiceFactory.getReservationService().getByIsoDateOrderByTimeSlot(isoDate);
		model.addAttribute("reservations", reservations);
		model.addAttribute("isoDate", isoDate);
		return "reservations";
	}

	@GetMapping("/thankYou")
	public String getThankYouPage() {
		return "thankYou";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/reservations")
	public String reserveNow(@ModelAttribute Reservation reservation) {
		ServiceFactory.getReservationService().create(reservation);
		return "redirect:/thankYou";
	}

	@GetMapping("/")
	public String showReservationForm(Model model) {
		Reservation reservation = new Reservation();
		model.addAttribute("reservation", reservation);
		model.addAttribute("timeslots", ServiceFactory.getReservationService().getAvailableTimeSlots(
				DateUtilities.formatDate(new Date(), DateFormat.DATE.toString(), Constants.TIMEZONE_ID)));
		return "index";
	}

}
