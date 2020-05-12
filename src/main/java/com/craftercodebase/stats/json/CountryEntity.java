package com.craftercodebase.stats.json;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CountryEntity {

	@Id
	private String iso_code;
	private String location;

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

}