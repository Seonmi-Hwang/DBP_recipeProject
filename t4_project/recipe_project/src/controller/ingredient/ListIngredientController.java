package controller.ingredient;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.ingredient.*;
import model.Ingredient;
import model.service.IngredientManager;


public class ListIngredientController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IngredientManager manager = IngredientManager.getInstance();
		String category = request.getParameter("category");
		
		List<Ingredient> ingreList = manager.findIngredientList(category);
//		List<Ingredient> ingreList = manager.findingredientname();
		request.setAttribute("ingreList", ingreList);
		request.setAttribute("category", 
				IngredientSessionUtils.getcategory(request.getSession()));	
		return "/ingredient/selectForm.jsp";	
	}
}
