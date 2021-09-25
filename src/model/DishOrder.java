package model;

public class DishOrder {
	
	private Dish orderedDish;
	private int amountOrderedDish;
	private double totalPrice;
	private String dishName;
	
	public DishOrder(Dish orderedDish, int amountOrderedDish, double totalPrice) {
		this.orderedDish = orderedDish;
		this.amountOrderedDish = amountOrderedDish;
		this.totalPrice = totalPrice;
		
		dishName = orderedDish.getDishName();
	}

	//Getters y Setters
	public Dish getOrderedDish() {
		return orderedDish;
	}

	public void setOrderedDish(Dish orderedDish) {
		this.orderedDish = orderedDish;
	}

	public int getAmountOrderedDish() {
		return amountOrderedDish;
	}

	public void setAmountOrderedDish(int amountOrderedDish) {
		this.amountOrderedDish = amountOrderedDish;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	
	
	
}
