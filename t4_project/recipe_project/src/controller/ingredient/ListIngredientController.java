package controller.ingredient;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.ingredient.*;
import model.Ingredient;
import model.Recipe;
import model.service.IngredientManager;
import model.service.RecipeManager;


public class ListIngredientController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RecipeManager manager = RecipeManager.getInstance();
		String[] ingr = request.getParameterValues("ingre");
		
//		List<Ingredient> ingreList = manager.findIngredientList(category);
		List<Integer> recipeidList = manager.findRecommendRecipe(ingr);
		List<Recipe> recipeList = new ArrayList<Recipe>();
		Recipe r;
		for(int i:recipeidList) {
			r = manager.findRecipe(i);
			if(r.getResult_img()==null) {
				System.out.printf("%s", r);
				r.setResult_img("file:///C:/dbp/DBP_recipeProject/t4_project/recipe_project/WebContent/ingredient/recipelist.png");
			}
			recipeList.add(r);
		}
		
		request.setAttribute("recipeList", recipeList);
//		request.setAttribute("category", 
//				IngredientSessionUtils.getcategory(request.getSession()));	
		return "/recipe/list.jsp";	
	}
}
