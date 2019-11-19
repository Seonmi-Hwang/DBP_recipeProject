package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.member.MemberSessionUtils;
import model.Member;
import model.service.MemberManager;

public class MainController implements Controller {
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		// �α��� ���� Ȯ��
    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login/form";		// login form ��û���� redirect
        }
    	
		MemberManager manager = MemberManager.getInstance();

		// ���� �α����� ����� ID�� request�� �����Ͽ� ����
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));		

		// ����� ����Ʈ ȭ������ �̵�(forwarding)
		return "/main.jsp";        
    }
}
