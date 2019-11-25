package controller.recipe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Ingredient;
import model.Procedure;
import model.Recipe;
import model.service.IngredientManager;
import model.service.MemberManager;
import model.service.RecipeManager;

public class AddRecipeController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(AddRecipeController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {	
    		// GET request: 레시피 추가 form 요청	
    		// 원래는 AddRecipeFormController가 처리하던 작업을 여기서 수행
    		String category_id = request.getParameter("category_id");

    		log.debug("AddForm(Recipe) Request : {}", category_id);
    		
			// 현재 로그인한 사용자 ID를 request에 저장하여 전달
			request.setAttribute("curMemberId", 
					MemberSessionUtils.getLoginMemberId(request.getSession()));		
			request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
    		request.setAttribute("category_id", category_id); // recipe/addForm
    		return "/recipe/addForm.jsp";
	    }	
		
		
		/* POST (/recipe/addForm.jsp에서 레시피 등록버튼 누른 후 폼 입력 값 전송 request*/
		
		/* writer 설정 위해 */
		MemberManager mManager = MemberManager.getInstance();
		String email_id = MemberSessionUtils.getLoginMemberId(request.getSession());
		int writerId = mManager.findMember(email_id).getMember_id();
		String writer = mManager.findMember(email_id).getMname();
		Date nowTime = new Date();
		
		/* 사용자로부터 입력받아온 재료 정보와 조리 과정을 recipe객체의 멤버변수에 맞게 */
		String[] iname = request.getParameterValues("iname");
		String[] quantity = request.getParameterValues("quantity");
		String[] procText = request.getParameterValues("proc_text");
		String[] procId = request.getParameterValues("proc_id");
		String[] img_url = request.getParameterValues("img_url");
		
		IngredientManager imanager = IngredientManager.getInstance();
		List<Ingredient> iList = new ArrayList<>();
		for (int i = 0; i < iname.length; i++) {
			Ingredient ingredient = new Ingredient();
			if (iname[i] == null || iname[i].trim().equals("")) {	// ""만 들어올 경우를 방지
				continue;
			}
			ingredient.setIngredient_id(imanager.findIdByName(iname[i]));
			ingredient.setQuantity(quantity[i]);
			iList.add(ingredient);
		}

		/* 조리 과정들의 배열 */
		List<Procedure> pList = new ArrayList<>();
		for (int i = 0; i < procText.length; i++) {
			Procedure proc = new Procedure(); 
			if (procId[i] == null || procId[i].trim().equals("")) {	// ""만 들어올 경우를 방지
				continue;
			}
			proc.setProc_Id(Integer.valueOf(procId[i]));
			proc.setText(procText[i]);
			proc.setImg_url(img_url[i]);
			pList.add(proc);
		}
		/* 조리 과정을 proc_id를 기준으로 오름차순으로 정렬*/
		pList.sort(new Comparator<Procedure>() {

			@Override
			public int compare(Procedure arg0, Procedure arg1) {
				// TODO Auto-generated method stub
				 int age0 = arg0.getProc_Id();
                 int age1 = arg1.getProc_Id();
                 if (age0 == age1)
                       return 0;
                 else if (age0 > age1)
                       return 1;
                 else
                       return -1;
			}
			
		});
		
		/* request로 받아온 parameter들로 recipe 객체 생성*/
		Recipe recipe = new Recipe(
				0, //recipe_id는 DAO에서 시퀀스로 설정. 그래서 필요 X.
				Integer.parseInt(request.getParameter("category_id")),
				request.getParameter("rname"),
				Integer.parseInt(request.getParameter("time")),
				request.getParameter("result_img"),
				0,
				pList,
				iList,
				null,
				writer,
				nowTime
		);

		log.debug("Create Recipe : {}", recipe);

		RecipeManager rmanager = RecipeManager.getInstance();
		int recipe_id = rmanager.create(recipe, writerId);
		
		recipe = rmanager.findRecipe(recipe_id);
		request.setAttribute("recipe", recipe);
		request.setAttribute("memberName", writer);
		request.setAttribute("curMemberId", email_id);	
		return "/recipe/view.jsp"; // 성공 시 작성한 레시피 보기 jsp로 redirect
	}

}
