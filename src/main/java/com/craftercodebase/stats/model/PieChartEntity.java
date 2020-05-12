package com.craftercodebase.stats.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PieChartEntity {

	@Id
	private int id;
	
	private String iso_code;
	
	private String location;

	private Date reported_date;

	private int total_cases;

	private int total_deaths;

	private int new_cases;

	private int new_deaths;

	private int new_tests;

	public int getId() {
		return id;
	}
	
	public String getIso_code() {
		return iso_code;
	}

	public String getLocation() {
		return location;
	}

	public Date getReported_date() {
		return reported_date;
	}

	public int getTotal_cases() {
		return total_cases;
	}

	public int getTotal_deaths() {
		return total_deaths;
	}

	public int getNew_cases() {
		return new_cases;
	}

	public int getNew_deaths() {
		return new_deaths;
	}

	public int getNew_tests() {
		return new_tests;
	}

}