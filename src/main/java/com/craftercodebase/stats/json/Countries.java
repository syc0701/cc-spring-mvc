package com.craftercodebase.stats.json;

import java.util.ArrayList;

public class Countries {

	private ArrayList<String> iso_code = new ArrayList<String>();
	private ArrayList<String> location = new ArrayList<String>();
	private ArrayList<Boolean> included = new ArrayList<Boolean>();

	public ArrayList<String> getIso_code() {
		return iso_code;
	}

	public void setIso_code(ArrayList<String> iso_codes) {
		this.iso_code = iso_codes;
	}

	public ArrayList<String> getLocation() {
		return location;
	}

	public void setLocation(ArrayList<String> location) {
		this.location = location;
	}

	public ArrayList<Boolean> getIncluded() {
		return included;
	}

	public void setIncluded(ArrayList<Boolean> included) {
		this.included = included;
	}

}
