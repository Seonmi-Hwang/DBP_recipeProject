package controller.recipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Recipe;
import model.service.RecipeManager;

public class AddRecipeController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(AddRecipeController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {	
    		// GET request: ȸ������ ���� form ��û	
    		// ������ UpdateRecipeFormController�� ó���ϴ� �۾��� ���⼭ ����
    		String recipe_id = request.getParameter("recipe_id");

    		log.debug("UpdateForm(Recipe) Request : {}", recipe_id);
  
//			// else (���� �Ұ����� ���) ����� ���� ȭ������ ���� �޼����� ����
//			request.setAttribute("updateFailed", true);
//			request.setAttribute("exception", 
//					new IllegalStateException("Ÿ���� ������ ������ �� �����ϴ�."));            
//			return "/recipe/";	// ����� ���� ȭ������ �̵� (forwarding)
	    }	
    	
		Recipe recipe = new Recipe(
				);

		log.debug("Create Recipe : {}", recipe);

		RecipeManager manager = RecipeManager.getInstance();
		manager.create(recipe);
		request.setAttribute("recipe", recipe);
		return "/recipe/view(owner).jsp"; // ���� �� �ۼ��� ������ ���� jsp�� redirect

	}

}
