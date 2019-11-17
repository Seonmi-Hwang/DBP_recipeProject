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
 
    	/* 우리는 GET아니라 POST니까 상관없을것같아서 주석 처리함
    	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 수정 form 요청	
    		// 원래는 UpdateUserFormController가 처리하던 작업을 여기서 수행
    		String updateId = request.getParameter("email_id");

    		log.debug("UpdateForm Request : {}", updateId);
    		
    		MemberManager manager = MemberManager.getInstance();
			Member member = manager.findMember(updateId);	// 수정하려는 사용자 정보 검색
			request.setAttribute("member", member);			

			HttpSession session = request.getSession();
			if (MemberSessionUtils.isLoginMember(updateId, session) ||
				MemberSessionUtils.isLoginMember("admin", session)) {
				// 현재 로그인한 사용자가 수정 대상 사용자이거나 관리자인 경우 -> 수정 가능
				return "/member/updateForm.jsp";   // 검색한 사용자 정보를 update form으로 전송     
			}    
			
			// else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("타인의 정보는 수정할 수 없습니다."));            
			return "/member/view.jsp";	// 사용자 보기 화면으로 이동 (forwarding)
	    }	
	    */
    	
    	// POST request (회원정보가 parameter로 전송됨)
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
