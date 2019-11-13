package model;

public class Prefer_ingredient {
	private int member_id;
	private int ingredient_id;
	private String prefer;
	
	public Prefer_ingredient() { }
	
	public Prefer_ingredient(int member_id, int ingredient_id,String prefer) {
		this.member_id = member_id;
		this.ingredient_id = ingredient_id;
		this.prefer = prefer;
	}
	
	public void update(Prefer_ingredient upprefer) {
		this.member_id = upprefer.member_id;
		this.ingredient_id = upprefer.ingredient_id;
		this.prefer = upprefer.prefer;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public int getIngredient_id() {
		return ingredient_id;
	}

	public void setIngredient_id(int ingredient_id) {
		this.ingredient_id = ingredient_id;
	}

	public String getPrefer() {
		return prefer;
	}

	public void setPrefer(String prefer) {
		this.prefer = prefer;
	}

	@Override
	public String toString() {
		return "Prefer_ingredient [member_id=" + member_id + ", ingredient_id=" + ingredient_id + ", prefer=" + prefer
				+ "]";
	}
	
}
