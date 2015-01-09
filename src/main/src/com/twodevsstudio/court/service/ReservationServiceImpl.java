package com.twodevsstudio.court.service;

import com.twodevsstudio.court.domain.*;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationServiceImpl implements ReservationService {
	public static final SportType TENNIS = new SportType(1, "Tennis");
	public static final SportType SOCCER = new SportType(2, "Soccer");

	private List<Reservation> reservations;
	private List<Court> courts;

	public ReservationServiceImpl() {
		courts = new ArrayList<Court>() {{
			add(new Court("Tennis #1"));
			add(new Court("Tennis #2"));
			add(new Court("Soccer #1"));
		}};
		reservations = new ArrayList<Reservation>() {{
			add(new Reservation(courts.get(0), new GregorianCalendar(2015, 0, 7).getTime(), 16, new Player("Roger", "N/A"), TENNIS));
			add(new Reservation(courts.get(0), new GregorianCalendar(2015, 0, 7).getTime(), 18, new Player("Corry", "N/A"), TENNIS));
			add(new Reservation(courts.get(0), new GregorianCalendar(2015, 0, 7).getTime(), 20, new Player("James", "N/A"), TENNIS));
			add(new Reservation(courts.get(2), new GregorianCalendar(2015, 0, 7).getTime(), 12, new Player("Bilbo", "N/A"), SOCCER));
		}};
	}

	@Override
	public List<Reservation> query(Court court) {
		List<Reservation> result = new ArrayList<>();
		for (Reservation each : reservations) {
			if (each.getCourt().equals(court))
				result.add(each);
		}
		return result;
	}

	@Override
	public void make(Reservation reservation) {
		for (Reservation each : reservations) {
			if (each.getCourt().equals(reservation.getCourt()) && each.getDate().equals(reservation.getDate()) && each.getHour() == reservation.getHour()) {
				throw new ReservationNotAvailableException(reservation);
			}
		}
		reservations.add(reservation);
	}

	@Override
	public void makePeriodic(PeriodicReservation periodicReservation) {
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(periodicReservation.getFromDate());

		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(periodicReservation.getToDate());

		while (fromCalendar.before(toCalendar)) {
			Reservation reservation = new Reservation();
			reservation.setCourt(periodicReservation.getCourt());
			reservation.setDate(fromCalendar.getTime());
			reservation.setHour(periodicReservation.getHour());
			reservation.setPlayer(periodicReservation.getPlayer());
			make(reservation);

			fromCalendar.add(Calendar.DATE, periodicReservation.getPeriod());
		}
	}

	@Override
	public List<SportType> getAllSportTypes() {
		return Arrays.asList(TENNIS, SOCCER);
	}

	@Override
	public SportType getSportType(int sportTypeId) {
		switch (sportTypeId) {
			case 1:
				return TENNIS;
			case 2:
				return SOCCER;
			default:
				System.out.println("NULL THROWN!!");
				return null;
		}
	}

	@Override
	public List<String> getAllCourtsNames() {
		return courts.stream().map(Court::getName).collect(Collectors.toList());
	}
}
