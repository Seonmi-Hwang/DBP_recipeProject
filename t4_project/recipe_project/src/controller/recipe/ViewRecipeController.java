package controller.recipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Recipe;
import model.service.MemberManager;
import model.service.RecipeManager;

public class ViewRecipeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// /recipe/list에서 넘겨준 recipe_id 받아서 recipe객체 생성 후 /recipe/view로 보냄
		int recipe_id = Integer.parseInt(request.getParameter("recipe_id"));
		
		RecipeManager manager = RecipeManager.getInstance();
		Recipe recipe= manager.findRecipe(recipe_id);	// 수정하려는 사용자 정보 검색
		request.setAttribute("recipe", recipe);		

		MemberManager mManager = MemberManager.getInstance();
		String emailId = MemberSessionUtils.getLoginMemberId(request.getSession());
		String memberName = mManager.findMember(emailId).getMname();
		request.setAttribute("memberName", memberName);
		
		return "/recipe/view.jsp";
	}

}
