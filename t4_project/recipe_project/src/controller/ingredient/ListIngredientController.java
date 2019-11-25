package controller.ingredient;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Recipe;
import model.service.RecipeManager;


public class ListIngredientController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		RecipeManager manager = RecipeManager.getInstance();
		String[] ingredientsList = request.getParameterValues("ingredients");
		String keywords = infoSelector(ingredientsList);
		
//		List<Ingredient> ingreList = manager.findIngredientList(category);
		List<Integer> recipeIdList = manager.findRecommendRecipe(ingredientsList);
		List<Recipe> recipeList = new ArrayList<Recipe>();
		String category_id = request.getParameter("category_id");
		
		Recipe r;
		for (int i : recipeIdList) {
			r = manager.findRecipe(i);
			recipeList.add(r);
		}

		// 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));
		request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
		request.setAttribute("currentPage", "searchRecRecipe");
		request.setAttribute("keywords", keywords);
		request.setAttribute("recipeList", recipeList);
		request.setAttribute("category_id", category_id);
		return "/recipe/list.jsp";	
	}
	
	private String infoSelector(String[] ingredientsList) {
		String value = "";
		
		for (int i = 0; i < ingredientsList.length; i++) {
			value += ingredientsList[i];
			
			if (i < ingredientsList.length - 1)
				value += ", ";
		}
		return value;
	}
}
