package controller.recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Recipe;
import model.service.RecipeManager;

public class SearchRecipeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		RecipeManager manager = RecipeManager.getInstance();
		request.setCharacterEncoding("utf-8");
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		String keyword = request.getParameter("keyword");
		
		List<Recipe> recipeList = manager.searchRecipeList(category_id, keyword);
		
		// 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));	
		request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
		
		request.setAttribute("recipeList", recipeList);
		request.setAttribute("keyword", keyword); 
		request.setAttribute("category_id", category_id);
		request.setAttribute("currentPage", "searchRecipe");
		
		return "/recipe/list.jsp";	
	}

}
