package com.twodevsstudio.court.web;

import com.twodevsstudio.court.domain.*;
import com.twodevsstudio.court.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservationForm")
@SessionAttributes("reservation")
public class ReservationFormController {
	private ReservationService reservationService;
	private ReservationValidator reservationValidator;

	@Autowired
	public ReservationFormController(ReservationService reservationService, ReservationValidator reservationValidator) {
		this.reservationService = reservationService;
		this.reservationValidator = reservationValidator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(SportType.class, new SportTypeEditor(reservationService));
	}

	@ModelAttribute("sportTypes")
	public List<SportType> populateSportTypes() {
		return reservationService.getAllSportTypes();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@RequestParam(required = false, value = "username") String username, Model model) {
		Reservation reservation = new Reservation();
		reservation.setPlayer(new Player(username, null));
		model.addAttribute("reservation", reservation);
		return "reservationForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("reservation") Reservation reservation, BindingResult result, SessionStatus status, Model model) {
		reservationValidator.validate(reservation, result);
		if (result.hasErrors()) {
			model.addAttribute("reservation", reservation);
			return "reservationForm";
		} else {
			reservationService.make(reservation);
			status.setComplete();
			return "reservationSuccess";
		}
	}
}
