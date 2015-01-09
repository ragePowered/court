package com.twodevsstudio.court.web;

import com.twodevsstudio.court.domain.Court;
import com.twodevsstudio.court.domain.Reservation;
import com.twodevsstudio.court.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {
	private ReservationService reservationService;

	@Autowired
	public ReservationQueryController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@ModelAttribute("courts")
	public List<String> populateCourts() {
		return reservationService.getAllCourtsNames();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		model.addAttribute("court", new Court());
		return "reservationQuery";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String setupForm(@ModelAttribute("court") Court court, Model model) {
		List<Reservation> reservations = Collections.emptyList();
		if (court != null) {
			reservations = reservationService.query(court);
		}
		model.addAttribute("reservations", reservations);
		return "reservationQuery";
	}
}
