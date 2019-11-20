package controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Member;
import model.Recipe;
import model.service.MemberManager;
import model.service.MemberNotFoundException;
import model.service.RecipeManager;

public class MyPageController implements Controller {
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// 로그인 여부 확인
    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login/form";		// login form 요청으로 redirect
        }
    	
    	// for 멤버 정보 출력
		MemberManager manager = MemberManager.getInstance();
		String email_id = request.getParameter("email_id");
    	Member member = manager.findMember(email_id);
    	request.setAttribute("member", member);		// 사용자 정보 저장
    	
    	// for 레시피 출력
		RecipeManager rManager = RecipeManager.getInstance();
		List<Recipe> recipeList = rManager.findUserRecipeList(email_id);
		request.setAttribute("recipeList", recipeList);
		
		return "/member/myPage.jsp";				// 사용자 보기 화면으로 이동
    }
}
