package controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.recipe.MemberSessionUtils;
import model.Member;
import model.service.MemberManager;

public class ListMemberController implements Controller {
	// private static final int countPerPage = 100;	// �� ȭ�鿡 ����� ����� ��

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		// �α��� ���� Ȯ��
    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login/form";		// login form ��û���� redirect
        }
    	
    	/*
    	String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}		
    	*/
    	
		MemberManager manager = MemberManager.getInstance();
		List<Member> memberList = manager.findMemberList();
		// List<User> userList = manager.findUserList(currentPage, countPerPage);

		// userList ��ü�� ���� �α����� ����� ID�� request�� �����Ͽ� ����
		request.setAttribute("memberList", memberList);				
		request.setAttribute("curMemberId", 
				MemberSessionUtils.getLoginMemberId(request.getSession()));		

		// ����� ����Ʈ ȭ������ �̵�(forwarding)
		return "/member/list.jsp";        
    }
}
