package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Member;
import model.service.ExistingMemberException;
import model.service.MemberManager;

public class RegisterMemberController implements Controller 	{
	private static final Logger log = LoggerFactory.getLogger(RegisterMemberController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member member = new Member(
			request.getParameter("email_Id"),
			request.getParameter("pw"),
			request.getParameter("mname"));
		
        log.debug("Create User : {}", member);

		try {
			MemberManager manager = MemberManager.getInstance();
			manager.create(member);
	        return "redirect:/member/list";		// ���� �� ����� ����Ʈ ȭ������ redirect
	        
		} catch (ExistingMemberException e) {		// ���� �߻� �� ȸ������ form���� forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("member", member);
			return "/member/registerForm.jsp";
		}
    }
}
