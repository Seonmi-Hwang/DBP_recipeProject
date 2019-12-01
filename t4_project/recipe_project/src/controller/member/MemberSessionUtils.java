package controller.member;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import model.Member;
import model.service.MemberManager;
import model.service.MemberNotFoundException;

public class MemberSessionUtils {

	public static final String USER_SESSION_KEY = "email_id";

    /* 현재 로그인한 사용자의 ID를 구함 */
    public static String getLoginMemberId(HttpSession session) {
        String email_id = (String)session.getAttribute(USER_SESSION_KEY);
        return email_id;
    }

    /* 로그인한 상태인지를 검사 */
    public static boolean hasLogined(HttpSession session) {
        if (getLoginMemberId(session) != null) {
            return true;
        }
        return false;
    }

    /* 현재 로그인한 사용자의 ID가 userId인지 검사 */
    public static boolean isLoginMember(String email_id, HttpSession session) {
        String loginMember = getLoginMemberId(session);
        if (loginMember == null) {
            return false;
        }
        return loginMember.equals(email_id);
    }
    
    /* 현재 로그인한 사용자의 이름을 구함*/
    public static String getLoginMemberName(HttpSession session) {
    	String memberId = getLoginMemberId(session);
    	
    	MemberManager manager = MemberManager.getInstance();
        Member member = null;
        	
        try {
			member = manager.findMember(memberId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemberNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return member.getMname();
    	
    	
    }
}
