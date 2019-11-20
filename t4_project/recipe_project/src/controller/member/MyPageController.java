package controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Member;
import model.Recipe;
import model.service.MemberManager;
import model.service.MemberNotFoundException;
import model.service.RecipeManager;

public class MyPageController implements Controller {
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// �α��� ���� Ȯ��
    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login/form";		// login form ��û���� redirect
        }
    	
    	// for ��� ���� ���
		MemberManager manager = MemberManager.getInstance();
		String email_id = request.getParameter("email_id");
    	Member member = manager.findMember(email_id);
    	request.setAttribute("member", member);		// ����� ���� ����
    	
    	// for ������ ���
		RecipeManager rManager = RecipeManager.getInstance();
		List<Recipe> recipeList = rManager.findUserRecipeList(email_id);
		request.setAttribute("recipeList", recipeList);
		
		return "/member/myPage.jsp";				// ����� ���� ȭ������ �̵�
    }
}
