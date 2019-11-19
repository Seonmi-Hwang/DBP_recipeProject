package controller.ingredient;

import javax.servlet.http.HttpSession;

public class IngredientSessionUtils {
	public static final String INGRE_SESSION_KEY = "category";
	
	public static String getcategory(HttpSession session) {
		String category = (String)session.getAttribute(INGRE_SESSION_KEY);
		return category;
	}

}
