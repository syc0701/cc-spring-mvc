package com.craftercodebase.mvc.web;

import java.util.List;

/**
 * This class holds the data displayed on the employee list web page and the
 * total number of rows.
 * 
 * It consists of members for displaying data in the Bootstrap table.
 * 
 * @author syc0701
 *
 */
public class RspDataTable {

	private long startNo;
	private long total;
	private long totalNotFiltered;
	private String sortOrder;
	private List rows;

	public long getStartNo() {
		return startNo;
	}

	public void setStartNo(long startNo) {
		this.startNo = startNo;
	}

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

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * Data rows displayed on the screen
	 * 
	 * @return rows
	 */
	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
