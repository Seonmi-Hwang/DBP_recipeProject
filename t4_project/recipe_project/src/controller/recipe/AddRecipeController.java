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
    		// GET request: 회원정보 수정 form 요청	
    		// 원래는 UpdateRecipeFormController가 처리하던 작업을 여기서 수행
    		String recipe_id = request.getParameter("recipe_id");

    		log.debug("UpdateForm(Recipe) Request : {}", recipe_id);
  
//			// else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
//			request.setAttribute("updateFailed", true);
//			request.setAttribute("exception", 
//					new IllegalStateException("타인의 정보는 수정할 수 없습니다."));            
//			return "/recipe/";	// 사용자 보기 화면으로 이동 (forwarding)
	    }	
    	
		Recipe recipe = new Recipe(
				);

		log.debug("Create Recipe : {}", recipe);

		RecipeManager manager = RecipeManager.getInstance();
		manager.create(recipe);
		request.setAttribute("recipe", recipe);
		return "/recipe/view(owner).jsp"; // 성공 시 작성한 레시피 보기 jsp로 redirect

	}

}
