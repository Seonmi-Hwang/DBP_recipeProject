package model;

public class Ingredient {
	private int recipe_id;
	private int ingredient_id;
	private String quantity;
	private String icategory;
	private String iname;
	
	public Ingredient() {	}
	
	public Ingredient(int ingredient_id, int recipe_id, String quantity, String icategory, String iname) {
		this.ingredient_id = ingredient_id;
		this.recipe_id = recipe_id;
		this.quantity = quantity;
		this.icategory = icategory;
		this.iname = iname;
	}

	public String getIcategory() {
		return icategory;
	}

	public void setIcategory(String icategory) {
		this.icategory = icategory;
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public int getIngredient_id() {
		return ingredient_id;
	}

	public void setIngredient_id(int ingredient_id) {
		this.ingredient_id = ingredient_id;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Ingredient [recipe_id=" + recipe_id + ", ingredient_id=" + ingredient_id + ", quantity=" + quantity
				+ ", icategory=" + icategory + ", iname=" + iname + "]";
	}

	

}
