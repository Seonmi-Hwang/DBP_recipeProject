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
		int categoryId = Integer.valueOf(request.getParameter("categoryId"));
		
		List<Recipe> recipeList = manager.findRecipeList(categoryId);
		
		request.setAttribute("recipeList", recipeList);
		return "/recipe/list.jsp";	
	}
}
