package controller.recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.RecipeManager;

public class ListRecipeController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		RecipeManager manager = RecipeManager.getInstance();
		List recipeList = manager.findRecipeList();
//		int category
		
		request.setAttribute("recipeList", recipeList);
		return "/recipe/list.jsp";
	}
}
