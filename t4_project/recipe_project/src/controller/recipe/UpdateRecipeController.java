package controller.recipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Recipe;
import model.Procedure;
import model.Ingredient;
import model.service.RecipeManager;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class UpdateRecipeController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(UpdateRecipeController.class);
	private static final int List = 0;
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if (request.getMethod().equals("GET")) {	
    		// GET request: 레시피 수정 form (초기 값)요청	
    		// 원래는 UpdateRecipeFormController가 처리하던 작업을 여기서 수행
    		int updateId = Integer.valueOf(request.getParameter("recipe_id"));

    		log.debug("UpdateForm Request : {}", updateId);
    		
    		RecipeManager manager = RecipeManager.getInstance();
			Recipe recipe= manager.findRecipe(updateId);	// 수정하려는 사용자 정보 검색
			request.setAttribute("recipe", recipe);			

			HttpSession session = request.getSession();
//			if (MemberSessionUtils.isLoginMember(updateId, session) ||
//				MemberSessionUtils.isLoginMember("admin", session)) {
//				// 현재 로그인한 사용자가 수정 대상 사용자이거나 관리자인 경우 -> 수정 가능
//				return "/member/updateForm.jsp";   // 검색한 사용자 정보를 update form으로 전송     
//			}    
			
	    }	
    	
		String[] procedure = request.getParameterValues("procedure");
//		List<Procedure> procList = new ArrayList<Procedure>();
		
    	// POST request (회원정보가 parameter로 전송됨)
//    	Recipe updatedRecipe = new Recipe(
//    		Integer.parseInt(request.getParameter("recipe_id")),
//    		Integer.parseInt(request.getParameter("category_id")),
//    		request.getParameter("rname"),
//    		request.getParameter("time"),
//    		request.getParameter("result_img"),
//    		request.getParameter("hits"),
//    		(List<Procedure>)procedure,
//    		(List<Ingredient>)request.getParameter("ingredients"),
//    		request.getParameter("writerId"),
//    		request.getParameter("createdDate"));
//
//    	log.debug("Update User : {}", updatedRecipe);
//
//		RecipeManager manager = RecipeManager.getInstance();
//		manager.update(updatedRecipe);			
        return "redirect:/recipe/view";		
	}

}
