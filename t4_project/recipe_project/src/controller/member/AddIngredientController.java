package controller.member;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.ingredient.IngredientSessionUtils;
import model.Ingredient;
import model.service.IngredientManager;

public class AddIngredientController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IngredientManager manager = IngredientManager.getInstance();
		
		List<Ingredient> ingreList = manager.findingredientname();
//		List<Ingredient> ingreList = manager.findingredientname();
		request.setAttribute("ingreList", ingreList);
		
		Set<String> set = new HashSet<String>();
		for (Ingredient i : ingreList) {
			set.add(i.getIcategory());
		}
		
		List<String> catList = new ArrayList<String>(set);
		request.setAttribute("catList", catList);
		
		return "/member/addForm.jsp";	
	}
}
