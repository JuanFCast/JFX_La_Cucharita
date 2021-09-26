package model;

import java.io.Serializable;

public class Ingredient implements Comparable<Ingredient>, Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private MEASUREMENT_TYPE measurement;
	private double amount;
	
	public Ingredient(String name, MEASUREMENT_TYPE measurement, double amount) {
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	//si son iguales retorna 0, si el primero es mayor al segundo retorna 1, si el segundo es mayor al primero retorna -1
	@Override
	public int compareTo(Ingredient o) {
		return name.compareTo(o.getName());
	}
	
	
	
}
