package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Member;
import model.service.MemberManager;
import model.service.MemberNotFoundException;

public class MyPageController implements Controller {
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// �α��� ���� Ȯ��
    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login/form";		// login form ��û���� redirect
        }
    	
		MemberManager manager = MemberManager.getInstance();
		String email_id = request.getParameter("email_id");

    	Member member = manager.findMember(email_id);
		
    	request.setAttribute("member", member);		// ����� ���� ����				
		return "/member/myPage.jsp";				// ����� ���� ȭ������ �̵�
    }
}
