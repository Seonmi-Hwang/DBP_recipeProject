package controller.member;

import javax.servlet.http.HttpSession;

public class MemberSessionUtils {

	public static final String USER_SESSION_KEY = "email_id";

    /* ���� �α����� ������� ID�� ���� */
    public static String getLoginMemberId(HttpSession session) {
        String email_id = (String)session.getAttribute(USER_SESSION_KEY);
        return email_id;
    }

    /* �α����� ���������� �˻� */
    public static boolean hasLogined(HttpSession session) {
        if (getLoginMemberId(session) != null) {
            return true;
        }
        return false;
    }

    /* ���� �α����� ������� ID�� userId���� �˻� */
    public static boolean isLoginMember(String email_id, HttpSession session) {
        String loginMember = getLoginMemberId(session);
        if (loginMember == null) {
            return false;
        }
        return loginMember.equals(email_id);
    }
}
