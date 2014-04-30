package com.tmg.uifwk.codegenerator;

public class RelationShip {
	private String type;
	private String name;
	private String cardinality;

	public RelationShip() {
		super();
	}

	public RelationShip(String type, String name, String cardinality) {
		super();
		this.type = type;
		this.name = name;
		this.cardinality = cardinality;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardinality() {
		return cardinality;
	}

	public void setCardinality(String cardinality) {
		this.cardinality = cardinality;
	}

}
