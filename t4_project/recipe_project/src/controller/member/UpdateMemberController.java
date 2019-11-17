package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.recipe.MemberSessionUtils;
import model.Member;
import model.service.MemberManager;

public class UpdateMemberController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateMemberController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
 
    	/* �츮�� GET�ƴ϶� POST�ϱ� ��������Ͱ��Ƽ� �ּ� ó����
    	if (request.getMethod().equals("GET")) {	
    		// GET request: ȸ������ ���� form ��û	
    		// ������ UpdateUserFormController�� ó���ϴ� �۾��� ���⼭ ����
    		String updateId = request.getParameter("email_id");

    		log.debug("UpdateForm Request : {}", updateId);
    		
    		MemberManager manager = MemberManager.getInstance();
			Member member = manager.findMember(updateId);	// �����Ϸ��� ����� ���� �˻�
			request.setAttribute("member", member);			

			HttpSession session = request.getSession();
			if (MemberSessionUtils.isLoginMember(updateId, session) ||
				MemberSessionUtils.isLoginMember("admin", session)) {
				// ���� �α����� ����ڰ� ���� ��� ������̰ų� �������� ��� -> ���� ����
				return "/member/updateForm.jsp";   // �˻��� ����� ������ update form���� ����     
			}    
			
			// else (���� �Ұ����� ���) ����� ���� ȭ������ ���� �޼����� ����
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("Ÿ���� ������ ������ �� �����ϴ�."));            
			return "/member/view.jsp";	// ����� ���� ȭ������ �̵� (forwarding)
	    }	
	    */
    	
    	// POST request (ȸ�������� parameter�� ���۵�)
    	Member updateMember = new Member(
    		request.getParameter("email_Id"),
    		request.getParameter("pw"),
    		request.getParameter("mname"));

    	log.debug("Update User : {}", updateMember);

		MemberManager manager = MemberManager.getInstance();
		manager.update(updateMember);			
        return "redirect:/member/list";			
    }
}
