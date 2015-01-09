package com.twodevsstudio.court.service;

import com.twodevsstudio.court.domain.Reservation;

public class ReservationNotAvailableException extends RuntimeException {
	private Reservation reservation;

	public ReservationNotAvailableException(Reservation reservation) {
		super("Reservation not available for " + reservation.getCourt().getName() +
				" | " + reservation.getDate() +
				" | " + reservation.getHour());
		this.reservation = reservation;
	}

	public Reservation getReservation() {
		return reservation;
	}
}
