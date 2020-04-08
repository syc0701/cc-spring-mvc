package com.craftercodebase.demo.web;

import java.util.List;

/**
 * Class for Bootstrap table
 * 
 * @author syc0701
 *
 */
public class RspDataTable {

	private long total;
	private long totalNotFiltered;
	private List rows;

	/**
	 * Total number of data
	 * 
	 * @return
	 */
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
