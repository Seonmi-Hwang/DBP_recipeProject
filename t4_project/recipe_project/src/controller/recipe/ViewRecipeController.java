package controller.recipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Recipe;
import model.service.RecipeManager;

public class ViewRecipeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// /recipe/list에서 넘겨준 recipe_id 받아서 recipe객체 생성 후 /recipe/view로 보냄
		int recipe_id = (int)request.getAttribute("recipe_id");
		
		RecipeManager manager = RecipeManager.getInstance();
		Recipe recipe = manager.findRecipe(recipe_id);
		
		request.setAttribute("recipe", recipe);
		
		return "/recipe/view.jsp";
	}

}
