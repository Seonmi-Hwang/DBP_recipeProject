package controller.ingredient;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Ingredient;
import model.service.IngredientManager;
import model.service.IngredientNotFoundException;

public class ViewIngredientController implements Controller{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		IngredientManager manager = IngredientManager.getInstance();
		String ingreId = request.getParameter("ingredient_id");
	
		Ingredient ingre = null;
		try {
			ingre = manager.findIngredient(Integer.parseInt(ingreId));	// 사용자 정보 검색
		} catch (IngredientNotFoundException e) {				
	        return "redirect:/user/list";
		}	
		
		request.setAttribute("ingre", ingre);		// 사용자 정보 저장				
		return "/ingredient/selectForm.jsp";				// 사용자 보기 화면으로 이동
		}
}
