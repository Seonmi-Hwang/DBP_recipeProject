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
    		// GET request: ������ ���� form (�ʱ� ��)��û	
    		// ������ UpdateRecipeFormController�� ó���ϴ� �۾��� ���⼭ ����
    		int updateId = Integer.valueOf(request.getParameter("recipe_id"));

    		log.debug("UpdateForm Request : {}", updateId);
    		
    		RecipeManager manager = RecipeManager.getInstance();
			Recipe recipe= manager.findRecipe(updateId);	// �����Ϸ��� ����� ���� �˻�
			request.setAttribute("recipe", recipe);			

			HttpSession session = request.getSession();
//			if (MemberSessionUtils.isLoginMember(updateId, session) ||
//				MemberSessionUtils.isLoginMember("admin", session)) {
//				// ���� �α����� ����ڰ� ���� ��� ������̰ų� �������� ��� -> ���� ����
//				return "/member/updateForm.jsp";   // �˻��� ����� ������ update form���� ����     
//			}    
			
	    }	
    	
		String[] procedure = request.getParameterValues("procedure");
//		List<Procedure> procList = new ArrayList<Procedure>();
		
    	// POST request (ȸ�������� parameter�� ���۵�)
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
