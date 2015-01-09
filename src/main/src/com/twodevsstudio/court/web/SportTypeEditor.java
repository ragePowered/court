package com.twodevsstudio.court.web;

import com.twodevsstudio.court.service.ReservationService;

import java.beans.PropertyEditorSupport;

public class SportTypeEditor extends PropertyEditorSupport {
	private ReservationService reservationService;

	public SportTypeEditor(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(reservationService.getSportType(Integer.parseInt(text)));
	}
}
