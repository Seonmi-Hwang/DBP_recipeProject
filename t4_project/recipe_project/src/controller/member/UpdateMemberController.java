package controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Member;
import model.Recipe;
import model.service.IngredientManager;
import model.service.MemberManager;
import model.service.RecipeManager;

public class UpdateMemberController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateMemberController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
 
    	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 수정 form 요청	
    		// 원래는 UpdateUserFormController가 처리하던 작업을 여기서 수행
    		String updateId = request.getParameter("email_id");

    		log.debug("UpdateForm Request : {}", updateId);
    		
    		MemberManager manager = MemberManager.getInstance();
			Member member = manager.findMember(updateId);	// 수정하려는 사용자 정보 검색
			request.setAttribute("member", member);

			// for 비선호 재료 출력
	    	IngredientManager imanager = IngredientManager.getInstance();
	    	String nonPrefer = imanager.findIngredient(member.getNonPrefer());
	    	request.setAttribute("nonPrefer", nonPrefer);
			
			HttpSession session = request.getSession();
			if (MemberSessionUtils.isLoginMember(updateId, session) ||
				MemberSessionUtils.isLoginMember("admin", session)) {
				// 현재 로그인한 사용자가 수정 대상 사용자이거나 관리자인 경우 -> 수정 가능
				return "/member/updateForm.jsp";   // 검색한 사용자 정보를 update form으로 전송 (forwarding)
			}    
			
			// else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("타인의 정보는 수정할 수 없습니다."));            
			return "/member/myPage.jsp";	// 사용자 보기 화면으로 이동 (forwarding)
	    }	
    	
    	// POST request (회원정보가 parameter로 전송됨)
    	IngredientManager imanager = IngredientManager.getInstance();
    	String ingredientInput = request.getParameter("nonPrefer");
    	int nonPrefer = imanager.findIdByName(ingredientInput);
    	
    	Member updateMember = new Member(
    		request.getParameter("email_id"),
    		request.getParameter("pw"),
    		request.getParameter("mname"),
    		nonPrefer);

    	log.debug("Update User : {}", updateMember);
    	
		MemberManager manager = MemberManager.getInstance();
		manager.update(updateMember);
		
		// for 멤버 정보 출력
		String email_id = request.getParameter("email_id");
    	Member member = manager.findMember(email_id);
    	request.setAttribute("member", member);		// 사용자 정보 저장
    	
    	// for 비선호 재료 출력
    	request.setAttribute("nonPrefer", ingredientInput);
    	
    	// for 레시피 출력
		RecipeManager rManager = RecipeManager.getInstance();
		List<Recipe> recipeList = rManager.findUserRecipeList(email_id);
		request.setAttribute("recipeList", recipeList);
		
		// 오른쪽 상단에 myPage 링크 띄우기 위한 코드
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));	
		request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
		
        return "/member/myPage.jsp";			
    }
}
