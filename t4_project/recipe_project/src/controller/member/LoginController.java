package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.MemberManager;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String email_id = request.getParameter("email_id");
		String pw = request.getParameter("pw");
		
		try {
			// 모델에 로그인 처리를 위임
			MemberManager manager = MemberManager.getInstance();
			manager.login(email_id, pw);
	
			// 세션에 사용자 이이디 저장
			HttpSession session = request.getSession();
            session.setAttribute(MemberSessionUtils.USER_SESSION_KEY, email_id);
            
            return "redirect:/main";			
		} catch (Exception e) {
			/* UserNotFoundException이나 PasswordMismatchException 발생 시
			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/member/loginForm.jsp";			
		}	
    }
}
