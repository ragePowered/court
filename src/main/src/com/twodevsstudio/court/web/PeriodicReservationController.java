package com.twodevsstudio.court.web;

import com.twodevsstudio.court.domain.PeriodicReservation;
import com.twodevsstudio.court.domain.PeriodicReservationValidator;
import com.twodevsstudio.court.domain.Player;
import com.twodevsstudio.court.domain.SportType;
import com.twodevsstudio.court.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/periodicReservationForm")
@SessionAttributes("reservation")
public class PeriodicReservationController {
	private ReservationService reservationService;
	private PeriodicReservationValidator validator;

	private static final Map<Integer, String> pageForms = new HashMap<Integer, String>() {{
		put(0, "reservationCourtForm");
		put(1, "reservationTimeForm");
		put(2, "reservationPlayerForm");
	}};
	private static final Map<Integer, String> periods = new HashMap<Integer, String>() {{
		put(1, "Daily");
		put(7, "Weekly");
	}};

	public PeriodicReservationController() {
	}

	@Autowired
	public PeriodicReservationController(ReservationService reservationService, PeriodicReservationValidator validator) {
		this.reservationService = reservationService;
		this.validator = validator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		PeriodicReservation reservation = new PeriodicReservation();
		reservation.setPlayer(new Player());
		model.addAttribute("reservation", reservation);
		return "reservationCourtForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(HttpServletRequest request, HttpServletResponse response,
							 @ModelAttribute("reservation") PeriodicReservation reservation,
							 BindingResult result, SessionStatus status,
							 @RequestParam("_page") int currentPage, Model model) {
		if (request.getParameter("_cancel") != null) {
			return "welcome";
		} else if (request.getParameter("_finish") != null) {
			validator.validate(reservation, result);
			if (!result.hasErrors()) {
				reservationService.makePeriodic(reservation);
				status.setComplete();
				return "reservationSuccess";
			} else {
				return pageForms.get(currentPage);
			}
		} else {
			int targetPage = WebUtils.getTargetPage(request, "_target", currentPage);
			if (targetPage < currentPage) {
				return pageForms.get(targetPage);
			}

			switch (currentPage) {
				case 0:
					validator.validateCourt(reservation, result);
					break;
				case 1:
					validator.validateTime(reservation, result);
					break;
				case 2:
					validator.validatePlayer(reservation, result);
					break;
			}
			if (!result.hasErrors()) {
				return pageForms.get(targetPage);
			} else {
				for (FieldError each : result.getFieldErrors()) {
					System.out.println(each);
				}
				return pageForms.get(currentPage);
			}
		}
	}

	@ModelAttribute("periods")
	public Map<Integer, String> populatePeriods() {
		return periods;
	}
}
