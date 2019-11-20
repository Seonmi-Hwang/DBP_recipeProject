package controller.recipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Recipe;
import model.Procedure;
import model.Ingredient;
import model.service.RecipeManager;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class UpdateRecipeController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(UpdateRecipeController.class);
	private static final int List = 0;
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if (request.getMethod().equals("GET")) {	
    		// GET request: ������ ���� form (�ʱ� ��)��û	
    		// ������ UpdateRecipeFormController�� ó���ϴ� �۾��� ���⼭ ����
    		int updateId = Integer.parseInt(request.getParameter("recipe_id"));

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
			return "/recipe/updateForm.jsp";
	    }	
    	
		String writer = MemberSessionUtils.getLoginMemberName(request.getSession());
		Date nowTime = new Date();
		
		
//		List<Procedure> procList = (List<Procedure>)request.getParameterValues("procedure");
		String[] iname = request.getParameterValues("iname");
		String[] quantity = request.getParameterValues("quantity");
		String[] procText = request.getParameterValues("proc_text");
		String[] procId = request.getParameterValues("proc_id");
		
		List<Ingredient> iList = new ArrayList<>();
		for (int i = 0; i < iname.length; i++) {
			Ingredient ingredient = new Ingredient();
			ingredient.setIname(iname[i]);
			ingredient.setQuantity(quantity[i]);
			iList.add(ingredient);
		}
		
		List<Procedure> pList = new ArrayList<>();
		for (int i = 0; i < procText.length; i++) {
			Procedure proc = new Procedure();
			proc.setProc_Id(Integer.parseInt(procId[i]));
			proc.setText(procText[i]);
		}
		
		/* request�� �޾ƿ� parameter��� recipe ��ü ����*/
		Recipe updateRecipe = new Recipe(
				0, //recipe_id�� DAO���� �������� ����
				Integer.parseInt(request.getParameter("category_id")),
				request.getParameter("rname"),
				request.getParameter("time"),
				null,	//result_img�� ���� ���ε� �ϰ�..
				0,
				pList,
				iList,
				null,
				writer,
				nowTime
		);
		
		log.debug("Update Recipe : {}", updateRecipe);
    	
		RecipeManager manager = RecipeManager.getInstance();
		manager.update(updateRecipe);
		Recipe recipe = manager.findRecipe(Integer.parseInt(request.getParameter("recipe_id")));
		request.setAttribute("recipe", recipe);
		return "/recipe/view(owner).jsp";
	}

}
