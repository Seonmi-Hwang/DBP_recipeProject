package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Member;
import model.dao.MemberDAO;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * MemberDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
 */
public class MemberManager {
	private static MemberManager memberMan = new MemberManager();
	private MemberDAO memberDAO;

	private MemberManager() {
		try {
			memberDAO = new MemberDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static MemberManager getInstance() {
		return memberMan;
	}
	
	public int create(Member member) throws SQLException, ExistingMemberException {
		if (memberDAO.existingMember(member.getEmail_id()) == true) {
			throw new ExistingMemberException(member.getEmail_id() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		return memberDAO.create(member);
	}

	public int update(Member member) throws SQLException {
		return memberDAO.update(member);
	}	

	public int remove(String memberId) throws SQLException {
		return memberDAO.remove(memberId);
	}

	public Member findMember(String memberId)
		throws SQLException, MemberNotFoundException {
		Member member = memberDAO.findMember(memberId);
		
		if (member == null) {
			throw new MemberNotFoundException(memberId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}		
		return member;
	}
	
	public Member findMember(int memberId)
			throws SQLException, MemberNotFoundException {
			Member member = memberDAO.findMember(memberId);
			
			if (member == null) {
				throw new MemberNotFoundException(memberId + "�� �������� �ʴ� ���̵��Դϴ�.");
			}		
			return member;
		}

	public List<Member> findMemberList() throws SQLException {
			return memberDAO.findMemberList();
	}
	
	public List<Member> findMemberList(int currentPage, int countPerPage)
		throws SQLException {
		return memberDAO.findMemberList(currentPage, countPerPage);
	}

	public boolean login(String MemberId, String password)
		throws SQLException, MemberNotFoundException, PasswordMismatchException {
		Member member = findMember(MemberId);

		if (!member.matchPassword(password)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}
	
	public MemberDAO getMemberDAO() {
		return this.memberDAO;
	}
}
