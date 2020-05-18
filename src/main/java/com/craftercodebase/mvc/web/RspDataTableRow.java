package com.craftercodebase.mvc.web;

import java.util.Date;

public class RspDataTableRow {
	private int ranking;
	private String iso_code;
	private String location;
	private Date reported_date;
	private int total_cases;
	private int new_cases;
	private int new_deaths;
	private int total_deaths;

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getIso_code() {
		return iso_code;
	}

	public void setIso_code(String iso_code) {
		this.iso_code = iso_code;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getReported_date() {
		return reported_date;
	}

	public void setReported_date(Date reported_date) {
		this.reported_date = reported_date;
	}

	public int getTotal_cases() {
		return total_cases;
	}

	public void setTotal_cases(int total_cases) {
		this.total_cases = total_cases;
	}

	public int getNew_cases() {
		return new_cases;
	}

	public void setNew_cases(int new_cases) {
		this.new_cases = new_cases;
	}

	public int getNew_deaths() {
		return new_deaths;
	}

	public void setNew_deaths(int new_deaths) {
		this.new_deaths = new_deaths;
	}

	public int getTotal_deaths() {
		return total_deaths;
	}

	public void setTotal_deaths(int total_deaths) {
		this.total_deaths = total_deaths;
	}

	@Override
	public String toString() {
		return "RspDataTableRow [ranking=" + ranking + ", iso_code=" + iso_code + ", location=" + location + ", reported_date="
				+ reported_date + ", total_cases=" + total_cases + ", new_cases=" + new_cases + ", new_deaths=" + new_deaths
				+ ", total_deaths=" + total_deaths + "]";
	}

}
