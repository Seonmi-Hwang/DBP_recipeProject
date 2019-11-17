package controller.recipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Recipe;
import model.service.RecipeManager;

public class ViewRecipeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// /recipe/list에서 넘겨준 recipe_id 받아서 recipe객체 생성 후 /recipe/view로 보냄
		int recipe_id = Integer.valueOf(request.getParameter("recipe_id"));
		
		RecipeManager manager = RecipeManager.getInstance();
		Recipe recipe= manager.findRecipe(recipe_id);	// 수정하려는 사용자 정보 검색
		request.setAttribute("recipe", recipe);			

		HttpSession session = request.getSession();
		
		// !!구현!!현재 로그인 한 사용자가 레시피 작성자와 일치하면 return "/recipe/view(owner).jsp;
		
		
		return "/recipe/view.jsp";
	}

}
