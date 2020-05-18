package com.craftercodebase.test;

import java.io.Serializable;

public class TT implements Serializable {

	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TT [id=" + id + ", name=" + name + "]";
	}

}
