package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.recipe.MemberSessionUtils;

public class LogoutController implements Controller {
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//���ǿ� ����� ����� ���̵� �����ϰ� ������ ��ȿȭ �� 
		HttpSession session = request.getSession();
		session.removeAttribute(MemberSessionUtils.USER_SESSION_KEY);
		session.invalidate();		
        
        return "redirect:/member/list";
    }
}
