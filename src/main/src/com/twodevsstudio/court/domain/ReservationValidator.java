package com.twodevsstudio.court.domain;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Calendar;

@Component
public class ReservationValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Reservation.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "court", "required.courtName", "Court name is required");
		ValidationUtils.rejectIfEmpty(errors, "date", "required.date", "Date is required");
		ValidationUtils.rejectIfEmpty(errors, "hour", "required.hour", "Hour is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "player.name", "required.playerName", "Player name name is required");
		ValidationUtils.rejectIfEmpty(errors, "sportType", "required.sportType", "Sport type is required");

		Reservation reservation = (Reservation) target;

		if (reservation.getDate() != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(reservation.getDate());
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				if (reservation.getHour() < 8 || reservation.getHour() > 22) {
					errors.reject("invalid.holidayHour", "Invalid holiday hour");
				}
			} else {
				if (reservation.getHour() < 9 || reservation.getHour() > 21) {
					errors.reject("invalid.weekdayHour", "Invalid weekday hour");
				}
			}
		}
	}
}
