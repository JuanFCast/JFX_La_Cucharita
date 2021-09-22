package model;

public class Ingredient {
	
	private String name;
	private MEASUREMENT_TYPE measurement;
	private int amount;
	
	public Ingredient(String name, MEASUREMENT_TYPE measurement, int amount) {
		this.name = name;
		this.measurement = measurement;
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MEASUREMENT_TYPE getMeasurement() {
		return measurement;
	}
	public void setMeasurement(MEASUREMENT_TYPE measurement) {
		this.measurement = measurement;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}
