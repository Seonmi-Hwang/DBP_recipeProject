package controller.recipe;

import java.util.ArrayList;
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
			/*
			List<Recipe> pageRecipeList = new ArrayList<Recipe>();
			int totalCount = recipeList.size();
			
			// paging
			String prevPageNum = request.getParameter("prevPageNo");
			String pageNum = request.getParameter("pageNo");
			String nextPageNum = request.getParameter("nextPageNo");
			
			int pageSize = 9;
			int maxPage = (totalCount + (pageSize - 1)) / pageSize;
			
			int prevPageNo;
			int pageNo;			
			int nextPageNo;
			
			if (prevPageNum != null) {
				pageNo = Integer.parseInt(prevPageNum);
			} else if (pageNum != null) {
				pageNo = Integer.parseInt(pageNum);
			} else if (nextPageNum != null) {
				pageNo = Integer.parseInt(nextPageNum);
			} else {
				pageNo = 1;
				for (int i = 0; i < 9; i++)
				pageRecipeList.add(recipeList.get(i));
			}
			
			if (pageNo - 1 != 0) prevPageNo = pageNo - 1; // prevPageNo 없으면 null(0) return
			
			if (pageNo + 1 != maxPage) nextPageNo = pageNo + 1;

			// paging attribute 전달
			*/
			
			
			// 현재 로그인한 사용자 ID를 request에 저장하여 전달
			request.setAttribute("curMemberId", 
					MemberSessionUtils.getLoginMemberId(request.getSession()));		
			request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
			
			request.setAttribute("recipeList", recipeList);
			request.setAttribute("category_id", category_id);
			request.setAttribute("currentPage", "listRecipe");
			request.setAttribute("deleteComplete", -1);	

		} catch (Exception e) {
            request.setAttribute("noRecipe", true);
			request.setAttribute("exception", e);
		}
		
		return "/recipe/list.jsp";	
	}
}
