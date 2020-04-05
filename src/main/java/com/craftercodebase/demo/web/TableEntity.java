package com.craftercodebase.demo.web;

import java.util.List;

public class TableEntity {

	private long total;
	private long totalNotFiltered;
	private List rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotalNotFiltered() {
		return totalNotFiltered;
	}

	public void setTotalNotFiltered(long totalNotFiltered) {
		this.totalNotFiltered = totalNotFiltered;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
