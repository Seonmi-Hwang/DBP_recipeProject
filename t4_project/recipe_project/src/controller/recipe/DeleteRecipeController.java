package controller.recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Recipe;
import model.service.MemberManager;
import model.service.RecipeManager;

public class DeleteRecipeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub		
		RecipeManager manager = RecipeManager.getInstance();
		int recipe_id = Integer.parseInt(request.getParameter("recipe_id")); // 삭제할 레시피 아이디
		Recipe recipe = manager.findRecipe(recipe_id); // 삭제하려는 레시피 객체 가져옴
		
		// 레시피 삭제 수행
		int rslt = manager.remove(recipe_id);
		
		// list.jsp 출력 준비
		int category_id = recipe.getCategory_id();
		List<Recipe> recipeList = manager.findRecipeList(category_id);
		
		MemberManager mManager = MemberManager.getInstance();
		String email_id = MemberSessionUtils.getLoginMemberId(request.getSession());
		String writer = mManager.findMember(email_id).getMname();
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));		
		request.setAttribute("recipeList", recipeList);	
		request.setAttribute("category_id", category_id);
		request.setAttribute("currentPage", "listRecipe");
		request.setAttribute("deleteComplete", rslt);	
		request.setAttribute("memberName", writer);
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));	
		return "/recipe/list.jsp";
	}

}
