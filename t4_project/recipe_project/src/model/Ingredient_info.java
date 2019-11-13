package model;

public class Ingredient_info {
	private int ingredient_id;
	private String icategory;
	private String iname;
	
	public Ingredient_info() {	}
	
	public Ingredient_info(int ingredient_id, String icategory, String iname) {
		this.ingredient_id = ingredient_id;
		this.icategory = icategory;
		this.iname = iname;
	}
	
	public int getIngredient_id() {
		return ingredient_id;
	}

	public void setIngredient_id(int ingredient_id) {
		this.ingredient_id = ingredient_id;
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

	@Override
	public String toString() {
		return "Ingredient_info [ingredient_id=" + ingredient_id + ", icategory=" + icategory + ", iname=" + iname
				+ "]";
	}
}
