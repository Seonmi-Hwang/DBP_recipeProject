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
    		// GET request: ������ �߰� form ��û	
    		// ������ AddRecipeFormController�� ó���ϴ� �۾��� ���⼭ ����
    		String category_id = request.getParameter("category_id");

    		log.debug("AddForm(Recipe) Request : {}", category_id);
    		
			// ���� �α����� ����� ID�� request�� �����Ͽ� ����
			request.setAttribute("curMemberId", 
					MemberSessionUtils.getLoginMemberId(request.getSession()));		
			request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
    		request.setAttribute("category_id", category_id); // recipe/addForm
    		return "/recipe/addForm.jsp";
	    }	
		
		
		/* POST (/recipe/addForm.jsp���� ������ ��Ϲ�ư ���� �� �� �Է� �� ���� request*/
		
		/* writer ���� ���� */
		MemberManager mManager = MemberManager.getInstance();
		String email_id = MemberSessionUtils.getLoginMemberId(request.getSession());
		int writerId = mManager.findMember(email_id).getMember_id();
		String writer = mManager.findMember(email_id).getMname();
		Date nowTime = new Date();
		
		/* ����ڷκ��� �Է¹޾ƿ� ��� ������ ���� ������ recipe��ü�� ��������� �°� */
		String[] iname = request.getParameterValues("iname");
		String[] quantity = request.getParameterValues("quantity");
		String[] procText = request.getParameterValues("proc_text");
		String[] procId = request.getParameterValues("proc_id");
		String[] img_url = request.getParameterValues("img_url");
		
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
			proc.setImg_url(img_url[i]);
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
		Recipe recipe = new Recipe(
				0, //recipe_id�� DAO���� �������� ����. �׷��� �ʿ� X.
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
		return "/recipe/view.jsp"; // ���� �� �ۼ��� ������ ���� jsp�� redirect
	}

}
