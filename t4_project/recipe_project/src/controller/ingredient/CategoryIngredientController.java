package controller.ingredient;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Ingredient;
import model.service.IngredientManager;

public class CategoryIngredientController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IngredientManager manager = IngredientManager.getInstance();
		String category = request.getParameter(URLDecoder.decode("category", "UTF-8"));
		System.out.printf("%s",category);
		List<Ingredient> ingreList = manager.findIngredientList(category);
//		List<Ingredient> ingreList = manager.findingredientname();
		request.setAttribute("ingrecate", ingreList);
//		request.setAttribute("category", 
//				IngredientSessionUtils.getcategory(request.getSession()));	
		return "/ingredient/selectForm.jsp";
	}
}
