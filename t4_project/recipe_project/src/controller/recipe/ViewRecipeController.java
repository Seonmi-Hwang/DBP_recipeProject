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
		// /recipe/list���� �Ѱ��� recipe_id �޾Ƽ� recipe��ü ���� �� /recipe/view�� ����
		int recipe_id = Integer.parseInt(request.getParameter("recipe_id"));
		
		RecipeManager manager = RecipeManager.getInstance();
		Recipe recipe= manager.findRecipe(recipe_id);	// �����Ϸ��� ����� ���� �˻�
		request.setAttribute("recipe", recipe);		

		MemberManager mManager = MemberManager.getInstance();
		String emailId = MemberSessionUtils.getLoginMemberId(request.getSession());
		String memberName = mManager.findMember(emailId).getMname();
		request.setAttribute("memberName", memberName);
		
		return "/recipe/view.jsp";
	}

}
