package controller.recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Recipe;
import model.service.Paging;
import model.service.RecipeManager;

public class ListRecipeController implements Controller {
    
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		RecipeManager manager = RecipeManager.getInstance();
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		
		try {
			List<Recipe> recipeList = manager.findRecipeList(category_id);
			int totalCount = recipeList.size() - 1;
					
			Paging paging = new Paging();
			paging.setPageNo(1);
			paging.setPageSize(10);
			paging.setTotalCount(totalCount);
			
			// ���� �α����� ����� ID�� request�� �����Ͽ� ����
			request.setAttribute("curMemberId", 
					MemberSessionUtils.getLoginMemberId(request.getSession()));		
			request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
			
			request.setAttribute("recipeList", recipeList);
			request.setAttribute("category_id", category_id);
			request.setAttribute("currentPage", "listRecipe");
			request.setAttribute("deleteComplete", -1);	
			request.setAttribute("paging", paging);
		} catch (Exception e) {
            request.setAttribute("noRecipe", true);
			request.setAttribute("exception", e);
		}
		
		return "/recipe/list.jsp";	
	}
}
