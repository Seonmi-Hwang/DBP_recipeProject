package controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Member;
import model.Recipe;
import model.service.IngredientManager;
import model.service.MemberManager;
import model.service.RecipeManager;

public class UpdateMemberController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateMemberController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
 
    	if (request.getMethod().equals("GET")) {	
    		// GET request: ȸ������ ���� form ��û	
    		// ������ UpdateUserFormController�� ó���ϴ� �۾��� ���⼭ ����
    		String updateId = request.getParameter("email_id");

    		log.debug("UpdateForm Request : {}", updateId);
    		
    		MemberManager manager = MemberManager.getInstance();
			Member member = manager.findMember(updateId);	// �����Ϸ��� ����� ���� �˻�
			request.setAttribute("member", member);

			// for ��ȣ ��� ���
	    	IngredientManager imanager = IngredientManager.getInstance();
	    	String nonPrefer = imanager.findIngredient(member.getNonPrefer());
	    	request.setAttribute("nonPrefer", nonPrefer);
			
			HttpSession session = request.getSession();
			if (MemberSessionUtils.isLoginMember(updateId, session) ||
				MemberSessionUtils.isLoginMember("admin", session)) {
				// ���� �α����� ����ڰ� ���� ��� ������̰ų� �������� ��� -> ���� ����
				return "/member/updateForm.jsp";   // �˻��� ����� ������ update form���� ���� (forwarding)
			}    
			
			// else (���� �Ұ����� ���) ����� ���� ȭ������ ���� �޼����� ����
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("Ÿ���� ������ ������ �� �����ϴ�."));            
			return "/member/myPage.jsp";	// ����� ���� ȭ������ �̵� (forwarding)
	    }	
    	
    	// POST request (ȸ�������� parameter�� ���۵�)
    	IngredientManager imanager = IngredientManager.getInstance();
    	String ingredientInput = request.getParameter("nonPrefer");
    	int nonPrefer = imanager.findIdByName(ingredientInput);
    	
    	Member updateMember = new Member(
    		request.getParameter("email_id"),
    		request.getParameter("pw"),
    		request.getParameter("mname"),
    		nonPrefer);

    	log.debug("Update User : {}", updateMember);
    	
		MemberManager manager = MemberManager.getInstance();
		manager.update(updateMember);
		
		// for ��� ���� ���
		String email_id = request.getParameter("email_id");
    	Member member = manager.findMember(email_id);
    	request.setAttribute("member", member);		// ����� ���� ����
    	
    	// for ��ȣ ��� ���
    	request.setAttribute("nonPrefer", ingredientInput);
    	
    	// for ������ ���
		RecipeManager rManager = RecipeManager.getInstance();
		List<Recipe> recipeList = rManager.findUserRecipeList(email_id);
		request.setAttribute("recipeList", recipeList);
		
		// ������ ��ܿ� myPage ��ũ ���� ���� �ڵ�
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));	
		request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
		
        return "/member/myPage.jsp";			
    }
}
