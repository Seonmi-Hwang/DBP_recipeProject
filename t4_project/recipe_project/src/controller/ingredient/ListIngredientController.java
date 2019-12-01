package controller.ingredient;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Ingredient;
import model.Member;
import model.Recipe;
import model.service.IngredientManager;
import model.service.MemberManager;
import model.service.RecipeManager;


public class ListIngredientController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IngredientManager iManager = IngredientManager.getInstance();
		request.setCharacterEncoding("utf-8");
		RecipeManager manager = RecipeManager.getInstance();
		String[] ingredientsList = request.getParameterValues("ingredients");
		String keywords = infoSelector(ingredientsList);

		// ��� ���� �������� ��� DB�� �ִ� ��Ḧ ����ڿ��� ��Ÿ���� �ֱ� ���� by SM
		List<Ingredient> ingredientList = iManager.findAllingredientList();
		
		MemberManager mmanager = MemberManager.getInstance();
		String curMemberId = MemberSessionUtils.getLoginMemberId(request.getSession());
		int nonPrefer = mmanager.findMember(curMemberId).getNonPrefer();
		List<Integer> recipeIdList = manager.findRecommendRecipe(ingredientsList, nonPrefer);
		List<Recipe> recipeList = new ArrayList<Recipe>();
		String category_id = request.getParameter("category_id");
		
		Recipe r;
		for (int i : recipeIdList) {
			r = manager.findRecipe(i);
			recipeList.add(r);
		}

		// ���� �α����� ����� ID�� request�� �����Ͽ� ����
		request.setAttribute("curMemberId", 
				curMemberId);
		request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
		request.setAttribute("currentPage", "searchRecRecipe");
		request.setAttribute("keywords", keywords);
		
		// ��� ���� �������� ��� DB�� �ִ� ��Ḧ ����ڿ��� ��Ÿ���� �ֱ� ���� by SM
		request.setAttribute("ingredientList", ingredientList);
		
		request.setAttribute("recipeList", recipeList);
		request.setAttribute("category_id", category_id);
		return "/recipe/list.jsp";	
	}
	
	private String infoSelector(String[] ingredientsList) {
		String value = "";
		
		for (int i = 0; i < ingredientsList.length; i++) {
			value += ingredientsList[i];
			
			if (i < ingredientsList.length - 1)
				value += ", ";
		}
		return value;
	}
}
