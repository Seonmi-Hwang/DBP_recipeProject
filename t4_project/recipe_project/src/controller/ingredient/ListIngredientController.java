package controller.ingredient;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.ingredient.*;
import controller.member.MemberSessionUtils;
import model.Ingredient;
import model.Recipe;
import model.service.IngredientManager;
import model.service.RecipeManager;


public class ListIngredientController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RecipeManager manager = RecipeManager.getInstance();
		String[] ingr = request.getParameterValues("ingre");
		
//		List<Ingredient> ingreList = manager.findIngredientList(category);
		List<Integer> recipeidList = manager.findRecommendRecipe(ingr);
		List<Recipe> recipeList = new ArrayList<Recipe>();
		String category_id = request.getParameter("category_id");
		Recipe r;
		for(int i:recipeidList) {
			r = manager.findRecipe(i);
			if(r.getResult_img()==null) {
				System.out.printf("%s", r);
				r.setResult_img("file:///C:/dbp/DBP_recipeProject/t4_project/recipe_project/WebContent/ingredient/recipelist.png");
			}
			recipeList.add(r);
		}

		// 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));		
		request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
		request.setAttribute("recipeList", recipeList);
		request.setAttribute("category_id", category_id);	
		return "/recipe/list.jsp";	
	}
}
