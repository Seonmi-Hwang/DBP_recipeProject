package model;

public class Recipe_procedure {
	private int recipe_id;
	private int proc_id;
	private String text;
	private String img_url;
	
	public Recipe_procedure() {	}
	
	public Recipe_procedure(int recipe_id, int proc_id, String text, String img_url) {
		this.recipe_id = recipe_id;
		this.proc_id = proc_id;
		this.text = text;
		this.img_url = img_url;
	}
	
	public void update(Recipe_procedure uprecipe_procedure) {
		this.recipe_id = uprecipe_procedure.recipe_id;
		this.proc_id = uprecipe_procedure.proc_id;
		this.text = uprecipe_procedure.text;
		this.img_url = uprecipe_procedure.img_url;
	}

	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public int getProc_id() {
		return proc_id;
	}

	public void setProc_id(int proc_id) {
		this.proc_id = proc_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	@Override
	public String toString() {
		return "Recipe_procedure [recipe_id=" + recipe_id + ", proc_id=" + proc_id + ", text=" + text + ", img_url="
				+ img_url + "]";
	}
}
