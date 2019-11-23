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
		String[] ingr = request.getParameterValues("ingre");
		String rslt = infoSelector(ingr);
		
//		List<Ingredient> ingreList = manager.findIngredientList(category);
		List<Integer> recipeidList = manager.findRecommendRecipe(ingr);
		List<Recipe> recipeList = new ArrayList<Recipe>();
		String category_id = request.getParameter("category_id");
		
		Recipe r;
		for (int i : recipeidList) {
			r = manager.findRecipe(i);
			recipeList.add(r);
		}

		// 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));
		request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
		request.setAttribute("currentPage", "searchRecRecipe");
		request.setAttribute("keywords", rslt);
		request.setAttribute("recipeList", recipeList);
		request.setAttribute("category_id", category_id);
		return "/recipe/list.jsp";	
	}
	
	private String infoSelector(String[] ingr) {
		String value = "";
		
		for (int i = 0; i < ingr.length; i++) {
			value += ingr[i];
			
			if (i < ingr.length - 1)
				value += ", ";
		}
		return value;
	}
}
