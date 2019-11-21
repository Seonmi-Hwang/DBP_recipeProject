package controller.ingredient;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.ingredient.*;
import model.Ingredient;
import model.service.IngredientManager;
import model.service.RecipeManager;


public class ListIngredientController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RecipeManager manager = RecipeManager.getInstance();
		String[] ingr = request.getParameterValues("ingre");
		
		
//		List<Ingredient> ingreList = manager.findIngredientList(category);
		List<Integer> recipeList = manager.findRecommendRecipe(ingr);
		request.setAttribute("recipeList", recipeList);
//		request.setAttribute("category", 
//				IngredientSessionUtils.getcategory(request.getSession()));	
		return "/ingredient/Recommend.jsp";	
	}
}
