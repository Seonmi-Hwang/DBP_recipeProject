package model;

public class Prefer_ingredient {
	private int member_id;
	private int ingredient_id;
	private String prefer;
	private String icategory;
	private String iname;
	
	public Prefer_ingredient() { }
	
	public Prefer_ingredient(int ingredient_id, int member_id,String prefer, String icategory, String iname) {
		this.member_id = member_id;
		this.ingredient_id = ingredient_id;
		this.prefer = prefer;
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
				+ ", icategory=" + icategory + ", iname=" + iname + "]";
	}
	
}
