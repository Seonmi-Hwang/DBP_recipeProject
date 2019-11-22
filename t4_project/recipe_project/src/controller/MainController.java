package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.member.MemberSessionUtils;
import model.Recipe;
import model.service.RecipeManager;

public class MainController implements Controller {
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		// 로그인 여부 확인
    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login/form";		// login form 요청으로 redirect
        }
    	
		RecipeManager manager  = RecipeManager.getInstance();
		Recipe commonTopRecipe = manager.getTopRecipe(10);
		Recipe snsTopRecipe = manager.getTopRecipe(20);
		Recipe myTopRecipe = manager.getTopRecipe(30); 
		
		// 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));		
		request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
		request.setAttribute("commonTopRecipe", commonTopRecipe);
		request.setAttribute("snsTopRecipe", snsTopRecipe);
		request.setAttribute("myTopRecipe", myTopRecipe);
		
		// 사용자 리스트 화면으로 이동(forwarding)
		return "/main.jsp";        
    }
}
