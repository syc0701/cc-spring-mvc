package com.craftercodebase.stats.json;

import java.util.ArrayList;

public class ChartData2 {

	public static final String TOTAL_CASES = "Total Cases";
	public static final String TOTAL_DEATHS = "Total Deadths";
	public static final String NEW_CASES = "New Cases";
	public static final String NEW_DEATHS = "New Deadths";

	ArrayList<String> legends = new ArrayList<String>();
	ArrayList<Datas3> datas;
	ArrayList<String> countries;
	
	public ChartData2() {
		legends.add(TOTAL_CASES);
		legends.add(TOTAL_DEATHS);
		legends.add(NEW_CASES);
		legends.add(NEW_DEATHS);
	}

	public ArrayList<String> getLegends() {
		return legends;
	}

	public void setLegends(ArrayList<String> legends) {
		this.legends = legends;
	}

	public ArrayList<Datas3> getDatas() {
		return datas;
	}

	public void setDatas(ArrayList<Datas3> datas) {
		this.datas = datas;
	}

	public ArrayList<String> getCountries() {
		return countries;
	}

	public void setCountries(ArrayList<String> countries) {
		this.countries = countries;
	}

	public class Datas3 {

		String type = new String();
		ArrayList<Integer> entity = new ArrayList<Integer>();

		public Datas3(String type) {
			super();
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public ArrayList<Integer> getEntity() {
			return entity;
		}

		public void setEntity(ArrayList<Integer> entity) {
			this.entity = entity;
		}

	}

}
