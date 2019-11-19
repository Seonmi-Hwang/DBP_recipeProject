package controller.ingredient;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Ingredient;
import model.service.IngredientManager;


public class ListIngredientController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IngredientManager manager = IngredientManager.getInstance();
		String category = request.getParameter("category");
		
		List<Ingredient> ingreList = manager.findingredientname(category);
		
		request.setAttribute("ingreList", ingreList);
		return "/ingredient/selectForm.jsp";	
	}
}
