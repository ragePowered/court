package com.twodevsstudio.court.service;

import com.twodevsstudio.court.domain.Court;
import com.twodevsstudio.court.domain.PeriodicReservation;
import com.twodevsstudio.court.domain.Reservation;
import com.twodevsstudio.court.domain.SportType;

import java.util.List;

public interface ReservationService {
	public List<Reservation> query(Court court);
	public void make(Reservation reservation) throws ReservationNotAvailableException;
	public void makePeriodic(PeriodicReservation periodicReservation) throws ReservationNotAvailableException;
	public List<SportType> getAllSportTypes();
	public SportType getSportType(int sportTypeId);
	public List<String> getAllCourtsNames();
}
