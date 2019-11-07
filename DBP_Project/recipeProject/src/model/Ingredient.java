package model;

public class Ingredient {
	private int ingredient_id;
	private int recipe_id;
	private String quantity;
	
	public Ingredient() { }
	
	public Ingredient(int ingredient_id, int recipe_id, String quantity) {
		this.ingredient_id = ingredient_id;
		this.recipe_id = recipe_id;
		this.quantity = quantity;
	}
	
	public void update(Ingredient upingredient) {
		this.ingredient_id = upingredient.ingredient_id;
		this.recipe_id = upingredient.recipe_id;
		this.quantity = upingredient.quantity;
	}

	public int getIngredient_id() {
		return ingredient_id;
	}

	public void setIngredient_id(int ingredient_id) {
		this.ingredient_id = ingredient_id;
	}

	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Ingredient [ingredient_id=" + ingredient_id + ", recipe_id=" + recipe_id + ", quantity=" + quantity
				+ "]";
	}
	
}
