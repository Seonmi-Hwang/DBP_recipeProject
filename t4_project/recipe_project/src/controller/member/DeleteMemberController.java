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
import model.service.MemberManager;
import model.service.RecipeManager;

public class DeleteMemberController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(DeleteMemberController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String deleteId = request.getParameter("email_id");
    	log.debug("Delete User : {}", deleteId);

    	MemberManager manager = MemberManager.getInstance();		
		HttpSession session = request.getSession();
		
		if ((MemberSessionUtils.isLoginMember("admin", session) && 	// �α����� ����ڰ� �������̰� 	
			 !deleteId.equals("admin"))							// ���� ����� �Ϲ� ������� ���, 
			   || 												// �Ǵ� 
			(!MemberSessionUtils.isLoginMember("admin", session) &&  // �α����� ����ڰ� �����ڰ� �ƴϰ� 
			 MemberSessionUtils.isLoginMember(deleteId, session))) { // �α����� ����ڰ� ���� ����� ��� (�ڱ� �ڽ��� ����)
			
			RecipeManager rManager = RecipeManager.getInstance();
			List<Recipe> recipeList = rManager.findUserRecipeList(deleteId);
			for (Recipe r : recipeList) {
				rManager.remove(r.getRecipe_id());
			}
			manager.remove(deleteId);				// ����� ���� ����
			if (MemberSessionUtils.isLoginMember("admin", session))	// �α����� ����ڰ� ������ 	
				return "redirect:/member/list";		// ����� ����Ʈ�� �̵�
			else 									// �α����� ����ڴ� �̹� ������
				return "redirect:/member/logout";		// logout ó��
		}
		
		/* ������ �Ұ����� ��� */
		Member member = manager.findMember(deleteId);	// ����� ���� �˻�
		request.setAttribute("member", member);						
		request.setAttribute("deleteFailed", true);
		String msg = (MemberSessionUtils.isLoginMember("admin", session)) 
				   ? "�ý��� ������ ������ ������ �� �����ϴ�."		
				   : "Ÿ���� ������ ������ �� �����ϴ�.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "/member/myPage.jsp";		// ����� ���� ȭ������ �̵� (forwarding)	
	}
}
