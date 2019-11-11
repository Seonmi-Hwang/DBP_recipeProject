package model;

public class Category {
	private int category_id;
	private String cname;
	
	public Category() {}
	
	public Category(int category_id,String cname) {
		this.category_id = category_id;
		this.cname = cname;
	}
	
	public void update(Category upcategory) {
		this.category_id = upcategory.category_id;
		this.cname = upcategory.cname;
	}
	
	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", cname=" + cname + "]";
	}

}
