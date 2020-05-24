package com.craftercodebase.stats.json;

import java.util.ArrayList;

public class ChartCTData {

	private ArrayList<String> legends;
	private ArrayList<Datas> datas;
	private ArrayList<String> reported_date;

	public ArrayList<Datas> getDatas() {
		return datas;
	}

	public void setDatas(ArrayList<Datas> datasObject) {
		this.datas = datasObject;
	}

	public ArrayList<String> getLegends() {
		return legends;
	}

	public void setLegends(ArrayList<String> legends) {
		this.legends = legends;
	}

	public ArrayList<String> getReported_date() {
		return reported_date;
	}

	public void setReported_date(ArrayList<String> reported_date) {
		this.reported_date = reported_date;
	}

	public class Datas implements Comparable<Datas> {

		String location = new String();
		ArrayList<Object> entity = new ArrayList<Object>();
		
		int cases;

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public ArrayList<Object> getEntity() {
			return entity;
		}

		public void setEntity(ArrayList<Object> entity) {
			this.entity = entity;
		}

		public void addEntity(Object obj) {
			this.entity.add(obj);
			cases = (int) obj;
		}
		
		public int getCases() {
			return cases;
		}

		@Override
		public int compareTo(Datas o) {
			return o.getCases() - cases;
		}
	}
}
