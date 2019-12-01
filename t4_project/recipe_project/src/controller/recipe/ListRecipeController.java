package controller.recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Ingredient;
import model.Recipe;
import model.service.IngredientManager;
import model.service.RecipeManager;

public class ListRecipeController implements Controller {
    
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		RecipeManager rManager = RecipeManager.getInstance();
		IngredientManager iManager = IngredientManager.getInstance();
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		
		// 재료 맞춤 레시피일 경우 DB에 있는 재료를 사용자에게 나타내어 주기 위해 by SM
		List<Ingredient> ingredientList = iManager.findAllingredientList();
		
		try {
			List<Recipe> recipeList = rManager.findRecipeList(category_id);
			
			// 현재 로그인한 사용자 ID를 request에 저장하여 전달
			request.setAttribute("curMemberId", 
					MemberSessionUtils.getLoginMemberId(request.getSession()));		
			request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
			
			request.setAttribute("recipeList", recipeList);
			// 재료 맞춤 레시피일 경우 DB에 있는 재료를 사용자에게 나타내어 주기 위해 by SM
			request.setAttribute("ingredientList", ingredientList);
			
			request.setAttribute("category_id", category_id);
			request.setAttribute("currentPage", "listRecipe");
			request.setAttribute("deleteComplete", -1);	

		} catch (Exception e) {
            request.setAttribute("noRecipe", true);
			request.setAttribute("exception", e);
		}
		
		return "/recipe/list.jsp";	
	}
}
