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
import model.service.IngredientManager;
import model.service.MemberManager;
import model.service.RecipeManager;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
			request.setAttribute("curMemberId", 
					MemberSessionUtils.getLoginMemberId(request.getSession()));		
			return "/recipe/updateForm.jsp";
	    }	
		
		// POST request
    	
		/* writer ���� ���� */
		MemberManager mManager = MemberManager.getInstance();
		String email_id = MemberSessionUtils.getLoginMemberId(request.getSession());
		String writer = mManager.findMember(email_id).getMname();
		Date nowTime = new Date();
		
		/* ����ڷκ��� �Է¹޾ƿ� ��� ������ ���� ������ recipe��ü�� ��������� �°� */
		String[] iname = request.getParameterValues("iname");
		String[] quantity = request.getParameterValues("quantity");
		String[] procText = request.getParameterValues("proc_text");
		String[] procId = request.getParameterValues("proc_id");
		
		IngredientManager imanager = IngredientManager.getInstance();
		List<Ingredient> iList = new ArrayList<>();
		for (int i = 0; i < iname.length; i++) {
			Ingredient ingredient = new Ingredient();
			if (iname[i] == null || iname[i].trim().equals("")) {	// ""�� ���� ��츦 ����
				continue;
			}
			ingredient.setIngredient_id(imanager.findIdByName(iname[i]));
			ingredient.setQuantity(quantity[i]);
			iList.add(ingredient);
		}

		/* ���� �������� �迭 */
		List<Procedure> pList = new ArrayList<>();
		for (int i = 0; i < procText.length; i++) {
			Procedure proc = new Procedure(); 
			if (procId[i] == null || procId[i].trim().equals("")) {	// ""�� ���� ��츦 ����
				continue;
			}
			proc.setProc_Id(Integer.valueOf(procId[i]));
			proc.setText(procText[i]);
			proc.setImg_url(null);
			pList.add(proc);
		}
		/* ���� ������ proc_id�� �������� ������������ ����*/
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
		
		/* request�� �޾ƿ� parameter��� recipe ��ü ����*/
		Recipe updateRecipe = new Recipe(
				Integer.parseInt(request.getParameter("recipe_id")), //recipe_id�� DAO���� �������� ����. �׷��� �ʿ� X.
				30,
				request.getParameter("rname"),
				Integer.parseInt(request.getParameter("time")),
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
		
		request.setAttribute("memberName", writer);
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));		
		return "/recipe/view.jsp";
	}

}
