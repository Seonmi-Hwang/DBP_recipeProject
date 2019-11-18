package controller.recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Recipe;
import model.service.RecipeManager;

public class ListRecipeController implements Controller {
    
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		RecipeManager manager = RecipeManager.getInstance();
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		
		List<Recipe> recipeList = manager.findRecipeList(category_id);
		
		request.setAttribute("recipeList", recipeList);
		return "/recipe/list.jsp";	
	}
}
