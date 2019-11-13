package model;

import java.util.List;

public class Recipe {
	private int recipe_id;
	private int category_id;
	private String rname;
	private String time;
	private String result_img;
	private int hits;
	private List<Procedure> procedure;
	
	public Recipe() {}
	
	public Recipe (int recipe_id, int category_id, String rname, String time, String result_img, int hits, List<Procedure> procedure) {
		this.recipe_id = recipe_id;
		this.category_id = category_id;
		this.rname = rname;
		this.time = time;
		this.result_img = result_img;
		this.hits = hits;
		this.procedure = procedure;
	}

	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getResult_img() {
		return result_img;
	}

	public void setResult_img(String result_img) {
		this.result_img = result_img;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public List<Procedure> getProcedure() {
		return procedure;
	}

	public void setProcedure(List<Procedure> procedure) {
		this.procedure = procedure;
	}

	@Override
	public String toString() {
		return "Recipe [recipe_id=" + recipe_id + ", category_id=" + category_id + ", rname=" + rname + ", time="
				+ time + ", result_img=" + result_img + ", hits=" + hits + "]";
	}
}
