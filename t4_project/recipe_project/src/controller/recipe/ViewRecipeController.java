package controller.recipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Recipe;
import model.service.RecipeManager;

public class ViewRecipeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// /recipe/list���� �Ѱ��� recipe_id �޾Ƽ� recipe��ü ���� �� /recipe/view�� ����
		int recipe_id = Integer.valueOf(request.getParameter("recipe_id"));
		
		RecipeManager manager = RecipeManager.getInstance();
		Recipe recipe= manager.findRecipe(recipe_id);	// �����Ϸ��� ����� ���� �˻�
		request.setAttribute("recipe", recipe);			

		HttpSession session = request.getSession();
		
		// !!����!!���� �α��� �� ����ڰ� ������ �ۼ��ڿ� ��ġ�ϸ� return "/recipe/view(owner).jsp;
		
		
		return "/recipe/view.jsp";
	}

}
