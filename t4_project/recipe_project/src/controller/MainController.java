package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.member.MemberSessionUtils;
import model.Recipe;
import model.service.RecipeManager;

public class MainController implements Controller {
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		// �α��� ���� Ȯ��
    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login/form";		// login form ��û���� redirect
        }
    	
		RecipeManager manager  = RecipeManager.getInstance();
		Recipe commonTopRecipe = manager.getTopRecipe(10);
		Recipe snsTopRecipe = manager.getTopRecipe(20);
		Recipe myTopRecipe = manager.getTopRecipe(30); 
		
		// ���� �α����� ����� ID�� request�� �����Ͽ� ����
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));		
		request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
		request.setAttribute("commonTopRecipe", commonTopRecipe);
		request.setAttribute("snsTopRecipe", snsTopRecipe);
		request.setAttribute("myTopRecipe", myTopRecipe);
		
		// ����� ����Ʈ ȭ������ �̵�(forwarding)
		return "/main.jsp";        
    }
}
