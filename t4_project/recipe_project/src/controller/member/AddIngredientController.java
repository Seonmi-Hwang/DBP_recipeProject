package controller.member;

import java.util.List;

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
		String category = request.getParameter("category");
		
		List<Ingredient> ingreList = manager.findIngredientList(category);
//		List<Ingredient> ingreList = manager.findingredientname();
		request.setAttribute("ingreList", ingreList);
		
		return "/ingredient/selectForm.jsp";	
	}
}
