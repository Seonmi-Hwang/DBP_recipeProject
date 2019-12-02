package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Member;
import model.service.ExistingMemberException;
import model.service.IngredientManager;
import model.service.MemberManager;

public class RegisterMemberController implements Controller 	{
	private static final Logger log = LoggerFactory.getLogger(RegisterMemberController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	IngredientManager imanager = IngredientManager.getInstance();
    	String ingredientInput = request.getParameter("nonPrefer");
    	int nonPrefer = imanager.findIdByName(ingredientInput);
    	
		Member member = new Member(
			request.getParameter("email_id"),
			request.getParameter("pw"),
			request.getParameter("mname"),
			nonPrefer);
		
        log.debug("Create User : {}", member);

		try {
			MemberManager manager = MemberManager.getInstance();
			manager.create(member);
	        return "redirect:/member/login/form";
	        
		} catch (ExistingMemberException e) {		// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			return "/member/registerForm.jsp";
		}
    }
}
