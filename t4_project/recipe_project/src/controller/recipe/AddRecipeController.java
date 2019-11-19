package controller.recipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Recipe;
import model.service.RecipeManager;

public class AddRecipeController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(AddRecipeController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Recipe recipe = new Recipe();

		log.debug("Create Recipe : {}", recipe);

		RecipeManager manager = RecipeManager.getInstance();
		manager.create(recipe);
		request.setAttribute("category_id", recipe.getCategory_id());
		return "/recipe/list.jsp"; // 성공 시 '나만의 레시피' 리스트 화면으로 redirect

	}

}
