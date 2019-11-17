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
		// /recipe/list���� �Ѱ��� recipe_id �޾Ƽ� recipe��ü ���� �� /recipe/view�� ����
		int recipe_id = (int)request.getAttribute("recipe_id");
		
		RecipeManager manager = RecipeManager.getInstance();
		Recipe recipe = manager.findRecipe(recipe_id);
		
		request.setAttribute("recipe", recipe);
		
		return "/recipe/view.jsp";
	}

}
