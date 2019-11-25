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
		int recipe_id = Integer.parseInt(request.getParameter("recipe_id")); // ������ ������ ���̵�
		//Recipe recipe = rManager.findRecipe(recipe_id); // �����Ϸ��� ������ ��ü ������
		
		// ������ ���� ����
		int rslt = rManager.remove(recipe_id);
		
//		// list.jsp ��� �غ� (return "/recipe/list.jsp" �� ���)
//		int category_id = Integer.parseInt(request.getParameter("category_id")); // �� �ڵ带 ������ <c:param>�� category_id �Ǿ���� �� 
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

		
		//���� �������� ������ ����
		// for ��� ���� ���
		MemberManager manager = MemberManager.getInstance();
		String email_id = request.getParameter("email_id");
    	Member member = manager.findMember(email_id);
    	request.setAttribute("member", member);		// ����� ���� ����
    	
    	// for ������ ���
		rManager = RecipeManager.getInstance();
		List<Recipe> recipeList = rManager.findUserRecipeList(email_id);
		request.setAttribute("recipeList", recipeList);
		
		// ������ ��ܿ� myPage ��ũ ���� ���� �ڵ�
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));	
		request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
		
		request.setAttribute("email_id", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));	
		return "/member/myPage.jsp";

		//return "/recipe/list.jsp";

	}

}
