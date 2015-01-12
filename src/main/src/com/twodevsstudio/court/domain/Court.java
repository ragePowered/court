package com.twodevsstudio.court.domain;


public class Court {
	private int courtId;
	private String name;

	public Court() {
	}

	public Court(int courtId, String name) {
		this.courtId = courtId;
		this.name = name;
	}

	public int getCourtId() {
		return courtId;
	}

	public void setCourtId(int courtId) {
		this.courtId = courtId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Court court = (Court) o;

		if (courtId != court.courtId) return false;
		if (name != null ? !name.equals(court.name) : court.name != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return courtId;
	}

	@Override
	public String toString() {
		return name;
	}
}
