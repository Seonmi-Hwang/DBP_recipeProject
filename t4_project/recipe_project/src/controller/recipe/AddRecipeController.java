package controller.recipe;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Procedure;
import model.Recipe;
import model.service.RecipeManager;

public class AddRecipeController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(AddRecipeController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 수정 form 요청	
    		// 원래는 UpdateRecipeFormController가 처리하던 작업을 여기서 수행
    		String category_id = request.getParameter("category_id");

    		log.debug("AddForm(Recipe) Request : {}", category_id);
    		
    		request.setAttribute("category_id", category_id); // recipe/addForm
    		return "/recipe/addForm.jsp";
	    }	
		
		
		/* POST (/recipe/addForm.jsp에서 레시피 등록버튼 누른 후 폼 입력 값 전송 request*/
		String writer = MemberSessionUtils.getLoginMemberName(request.getSession());
		Date nowTime = new Date();
		
		
//		List<Procedure> procList = (List<Procedure>)request.getParameterValues("procedure");
		request.getParameterValues("ingredients");

		/* request로 받아온 parameter들로 recipe 객체 생성*/
//		Recipe recipe = new Recipe(
//				null, //recipe_id는 DAO에서 시퀀스로 설정
//				request.getParameter("category_id"),
//				request.getParameter("rname"),
//				request.getParameter("time"),
//				null,	//result_img는 파일 업로드 하고..
//				0,
//				null,
//				null,
//				writer,
//				nowTime
//				);

		/*
		 * public Recipe(int recipe_id, int category_id, String rname, String time, String result_img, int hits, List<Procedure> procedure, List<String> ingredients, String writer, Date createdDate) {
		this.recipe_id = recipe_id;
		this.category_id = category_id;
		this.rname = rname;
		this.time = time;
		this.result_img = result_img;
		this.hits = hits;
		this.procedure = procedure;
		this.ingredients = ingredients;
		this.writer = writer;
		this.createdDate = createdDate;
	}
		 * 
//		 */
//		log.debug("Create Recipe : {}", recipe);
//
//		RecipeManager manager = RecipeManager.getInstance();
//		manager.create(recipe);
//		request.setAttribute("recipe", recipe);
		return "/recipe/view(owner).jsp"; // 성공 시 작성한 레시피 보기 jsp로 redirect

	}

}
