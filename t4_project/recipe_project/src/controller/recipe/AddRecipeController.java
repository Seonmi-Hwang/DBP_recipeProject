package controller.recipe;

import java.util.ArrayList;
import java.util.Arrays;
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
import model.service.RecipeManager;

public class AddRecipeController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(AddRecipeController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {	
    		// GET request: ȸ������ ���� form ��û	
    		// ������ UpdateRecipeFormController�� ó���ϴ� �۾��� ���⼭ ����
    		String category_id = request.getParameter("category_id");

    		log.debug("AddForm(Recipe) Request : {}", category_id);
    		
    		request.setAttribute("category_id", category_id); // recipe/addForm
    		return "/recipe/addForm.jsp";
	    }	
		
		
		/* POST (/recipe/addForm.jsp���� ������ ��Ϲ�ư ���� �� �� �Է� �� ���� request*/
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
			if (iname[i] == null || iname[i].trim().equals("")) {	// ""�� ���� ��츦 ����
				continue;
			}
			ingredient.setIname(iname[i]);
			ingredient.setQuantity(quantity[i]);
			iList.add(ingredient);
		}

		List<Procedure> pList = new ArrayList<>();
		for (int i = 0; i < procText.length; i++) {
			Procedure proc = new Procedure(); 
			if (procId[i] == null || procId[i].trim().equals("")) {	// ""�� ���� ��츦 ����
				continue;
			}
			proc.setProc_Id(Integer.valueOf(procId[i]));

			proc.setText(procText[i]);
			pList.add(proc);
		}
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
		Recipe recipe = new Recipe(
				0, //recipe_id�� DAO���� �������� ����. �׷��� �ʿ� X.
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

		
		log.debug("Create Recipe : {}", recipe);

//		RecipeManager manager = RecipeManager.getInstance();
//		manager.create(recipe);
//		
		IngredientManager imanager = IngredientManager.getInstance();
		
		for (int i = 0; i < iList.size(); i++) {
			
		}
		request.setAttribute("recipe", recipe);
		return "/recipe/view(owner).jsp"; // ���� �� �ۼ��� ������ ���� jsp�� redirect

	}

}
