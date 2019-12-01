package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Member;

public class MemberDAO {
private JDBCUtil jdbcUtil = null;
	
	public MemberDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	/**
	 * ����� ���� ���̺� ���ο� ����� ����.
	 */
	public int create(Member member) throws SQLException {		
		String sql = "INSERT INTO member VALUES (mid_sequence.nextval, ?, ?, ?, ?)";		
		Object[] param = new Object[] { member.getMname(), member.getPw(), member.getEmail_id(), member.getNonPrefer()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}

	/**
	 * ������ ����� ������ ����.
	 */
	public int update(Member member) throws SQLException {
		String sql = "UPDATE member "
					+ "SET pw=?, mname=?, nonprefer=? " //member_id�� �⺻������ �ο��Ǵ°Ŷ� ���� �Ұ�. email_id�� ���̵� ���̶� ������ �� ���� ���� �ΰ��ۿ� ����
					+ "WHERE email_id=?";
		Object[] param = new Object[] {member.getPw(), member.getMname(), member.getNonPrefer(), member.getEmail_id()}; 
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	/**
	 * ����� ID�� �ش��ϴ� ����ڸ� ����.
	 */
	public int remove(String email_id) throws SQLException {
		String sql = "DELETE FROM member WHERE email_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {email_id});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	/**
	 * �־��� ����� ID�� �ش��ϴ� ����� ������ �����ͺ��̽����� ã�� Member ������ Ŭ������ 
	 * �����Ͽ� ��ȯ.
	 */
	public Member findMember(String email_id) throws SQLException {
        String sql = "SELECT member_id, pw, mname, nonprefer " //pw�� �����ص� �Ǵ����� �ǹ�
        			+ "FROM member "
        			+ "WHERE email_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {email_id});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				Member member = new Member(		// User ��ü�� �����Ͽ� �л� ������ ����
					rs.getInt("member_id"),
					email_id,
					rs.getString("pw"),
					rs.getString("mname"),
					rs.getInt("nonprefer"));
				return member;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * �־��� ����� ID�� �ش��ϴ� �г��� ��ȯ
	 */
	public String findMname(String email_id) throws SQLException {
        String sql = "SELECT mname " //pw�� �����ص� �Ǵ����� �ǹ�
        			+ "FROM member "
        			+ "WHERE email_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {email_id});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				String mname = rs.getString("pw");
				return mname;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * ��ü ����� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<Member> findMemberList() throws SQLException {
        String sql = "SELECT member_id, email_id, pw, mname, nonprefer " 
        		   + "FROM member "
        		   + "ORDER BY member_id";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Member> userList = new ArrayList<Member>();	// User���� ����Ʈ ����
			while (rs.next()) {
				Member member = new Member(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getInt("member_id"),
					rs.getString("email_id"),
					rs.getString("pw"),
					rs.getString("mname"),
					rs.getInt("nonprefer"));
				userList.add(member);				// List�� User ��ü ����
			}		
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * ��ü ����� ������ �˻��� �� ���� �������� �������� ����� ����� ���� �̿��Ͽ�
	 * �ش��ϴ� ����� �������� List�� �����Ͽ� ��ȯ.
	 */
//	public List<Member> findMemberList(int currentPage, int countPerPage) throws SQLException {
//		String sql = "SELECT pw, mname " //pw�� �����ص� �Ǵ����� �ǹ�
//    			+ "FROM member "
//    			+ "WHERE email_id=? "; 
//		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�� query�� ����
//				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll ����
//				ResultSet.CONCUR_READ_ONLY);						
//		
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();				// query ����			
//			int start = ((currentPage-1) * countPerPage) + 1;	// ����� ������ �� ��ȣ ���
//			if ((start >= 0) && rs.absolute(start)) {			// Ŀ���� ���� ������ �̵�
//				List<Member> memberList = new ArrayList<Member>();	// User���� ����Ʈ ����
//				do {
//					Member member = new Member(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
//						rs.getInt("member_id"),
//						rs.getString("email_id"),
//						rs.getString("pw"),
//						rs.getString("mname"));
//					memberList.add(member);							// ����Ʈ�� User ��ü ����
//				} while ((rs.next()) && (--countPerPage > 0));		
//				return memberList;							
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource ��ȯ
//		}
//		return null;
//	}
	
	/**
	 * �־��� ����� ID�� �ش��ϴ� ����ڰ� �����ϴ��� �˻� 
	 */
	public boolean existingMember(String email_id) throws SQLException {
		String sql = "SELECT count(*) FROM member WHERE email_id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {email_id});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}

}