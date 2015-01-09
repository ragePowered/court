package com.twodevsstudio.court.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Reservation {
	private Court court;
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Date date;
	private int hour;
	private Player player;
	private SportType sportType;

	public Reservation() {
	}

	public Reservation(Court court, Date date, int hour, Player player, SportType sportType) {
		this.court = court;
		this.date = date;
		this.hour = hour;
		this.player = player;
		this.sportType = sportType;
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public SportType getSportType() {
		return sportType;
	}

	public void setSportType(SportType sportType) {
		this.sportType = sportType;
	}
}
