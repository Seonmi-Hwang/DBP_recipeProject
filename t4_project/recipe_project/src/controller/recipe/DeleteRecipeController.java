package controller.recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Member;
import model.Recipe;
import model.service.MemberManager;
import model.service.RecipeManager;

public class DeleteRecipeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub		
		RecipeManager rManager = RecipeManager.getInstance();
		int recipe_id = Integer.parseInt(request.getParameter("recipe_id")); // 삭제할 레시피 아이디
		//Recipe recipe = rManager.findRecipe(recipe_id); // 삭제하려는 레시피 객체 가져옴
		
		// 레시피 삭제 수행
		int rslt = rManager.remove(recipe_id);
		
//		// list.jsp 출력 준비 (return "/recipe/list.jsp" 할 경우)
//		int category_id = Integer.parseInt(request.getParameter("category_id")); // 이 코드를 쓰려면 <c:param>에 category_id 실어줘야 함 
//		List<Recipe> recipeList = manager.findRecipeList(category_id);
//		
//		MemberManager mManager = MemberManager.getInstance();
//		String email_id = MemberSessionUtils.getLoginMemberId(request.getSession());
//		String writer = mManager.findMember(email_id).getMname();
//		request.setAttribute("curMemberId", 
//				MemberSessionUtils.getLoginMemberId(request.getSession()));		
//		request.setAttribute("memberName", writer);
//		request.setAttribute("recipeList", recipeList);	
//		request.setAttribute("category_id", category_id);
//		request.setAttribute("currentPage", "listRecipe");
		request.setAttribute("deleteComplete", rslt);	

		
		//마이 페이지로 가도록 수정
		// for 멤버 정보 출력
		MemberManager manager = MemberManager.getInstance();
		String email_id = request.getParameter("email_id");
    	Member member = manager.findMember(email_id);
    	request.setAttribute("member", member);		// 사용자 정보 저장
    	
    	// for 레시피 출력
		rManager = RecipeManager.getInstance();
		List<Recipe> recipeList = rManager.findUserRecipeList(email_id);
		request.setAttribute("recipeList", recipeList);
		
		// 오른쪽 상단에 myPage 링크 띄우기 위한 코드
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));	
		request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
		
		request.setAttribute("email_id", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));	
		return "/member/myPage.jsp";

		//return "/recipe/list.jsp";

	}

}
