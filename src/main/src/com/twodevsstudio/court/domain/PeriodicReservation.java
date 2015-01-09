package com.twodevsstudio.court.domain;

import java.util.Date;

public class PeriodicReservation {
	private Court court;
	private Date fromDate;
	private Date toDate;
	private int period;
	private int hour;
	private Player player;

	public PeriodicReservation() {
	}

	public PeriodicReservation(Court court, Date fromDate, Date toDate, int period, int hour, Player player) {
		this.court = court;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.period = period;
		this.hour = hour;
		this.player = player;
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
