package model;

public class Users_recipe {
	private int recipe_id;
	private int member_id;
	private String createdDate;
	
	public Users_recipe() {	}
	
	public Users_recipe(int recipe_id, int member_id, String createdDate) {	
		this.recipe_id = recipe_id;
		this.member_id = member_id;
		this.createdDate = createdDate;
	}
	
	public void update(Users_recipe upusers_recipe) {
		this.recipe_id = upusers_recipe.recipe_id;
		this.member_id = upusers_recipe.member_id;
		this.createdDate = upusers_recipe.createdDate;
	}

	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Users_recipe [recipe_id=" + recipe_id + ", member_id=" + member_id + ", createdDate=" + createdDate
				+ "]";
	}
	
}
