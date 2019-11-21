package controller.recipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.service.MemberManager;
import model.service.RecipeManager;

public class DeleteRecipeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		MemberManager mManager = MemberManager.getInstance();
		String writerEmailId = MemberSessionUtils.getLoginMemberId(request.getSession());
		int writerId = mManager.findMember(writerEmailId).getMember_id();
		
		RecipeManager rmanager = RecipeManager.getInstance();
		int rslt = rmanager.remove(writerId);
		
		request.setAttribute("deleteComplete", rslt);
		
		return "/recipe/list.jsp";
	}

}
