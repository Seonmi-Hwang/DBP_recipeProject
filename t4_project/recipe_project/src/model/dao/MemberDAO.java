package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Member;

public class MemberDAO {
private JDBCUtil jdbcUtil = null;
	
	public MemberDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	/**
	 * 사용자 관리 테이블에 새로운 사용자 생성.
	 */
	public int create(Member member) throws SQLException {		
		String sql = "INSERT INTO member VALUES (mid_sequence.nextval, ?, ?, ?, ?)";		
		Object[] param = new Object[] { member.getMname(), member.getPw(), member.getEmail_id(), member.getNonPrefer()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}

	/**
	 * 기존의 사용자 정보를 수정.
	 */
	public int update(Member member) throws SQLException {
		String sql = "UPDATE member "
					+ "SET pw=?, mname=?, nonprefer=? " //member_id는 기본적으로 부여되는거라 수정 불가. email_id는 아이디 값이라 변경할 수 없음 따라서 두개밖에 없음
					+ "WHERE email_id=?";
		Object[] param = new Object[] {member.getPw(), member.getMname(), member.getNonPrefer(), member.getEmail_id()}; 
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/**
	 * 사용자 ID에 해당하는 사용자를 삭제.
	 */
	public int remove(String email_id) throws SQLException {
		String sql = "DELETE FROM member WHERE email_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {email_id});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/**
	 * 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 Member 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public Member findMember(String email_id) throws SQLException {
        String sql = "SELECT member_id, pw, mname, nonprefer " //pw를 전달해도 되는지는 의문
        			+ "FROM member "
        			+ "WHERE email_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {email_id});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				Member member = new Member(		// User 객체를 생성하여 학생 정보를 저장
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
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 주어진 사용자 ID에 해당하는 닉네임 반환
	 */
	public String findMname(String email_id) throws SQLException {
        String sql = "SELECT mname " //pw를 전달해도 되는지는 의문
        			+ "FROM member "
        			+ "WHERE email_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {email_id});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				String mname = rs.getString("pw");
				return mname;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 전체 사용자 정보를 검색하여 List에 저장 및 반환
	 */
	public List<Member> findMemberList() throws SQLException {
        String sql = "SELECT member_id, email_id, pw, mname, nonprefer " 
        		   + "FROM member "
        		   + "ORDER BY member_id";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Member> userList = new ArrayList<Member>();	// User들의 리스트 생성
			while (rs.next()) {
				Member member = new Member(			// User 객체를 생성하여 현재 행의 정보를 저장
					rs.getInt("member_id"),
					rs.getString("email_id"),
					rs.getString("pw"),
					rs.getString("mname"),
					rs.getInt("nonprefer"));
				userList.add(member);				// List에 User 객체 저장
			}		
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 전체 사용자 정보를 검색한 후 현재 페이지와 페이지당 출력할 사용자 수를 이용하여
	 * 해당하는 사용자 정보만을 List에 저장하여 반환.
	 */
//	public List<Member> findMemberList(int currentPage, int countPerPage) throws SQLException {
//		String sql = "SELECT pw, mname " //pw를 전달해도 되는지는 의문
//    			+ "FROM member "
//    			+ "WHERE email_id=? "; 
//		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil에 query문 설정
//				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 가능
//				ResultSet.CONCUR_READ_ONLY);						
//		
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();				// query 실행			
//			int start = ((currentPage-1) * countPerPage) + 1;	// 출력을 시작할 행 번호 계산
//			if ((start >= 0) && rs.absolute(start)) {			// 커서를 시작 행으로 이동
//				List<Member> memberList = new ArrayList<Member>();	// User들의 리스트 생성
//				do {
//					Member member = new Member(			// User 객체를 생성하여 현재 행의 정보를 저장
//						rs.getInt("member_id"),
//						rs.getString("email_id"),
//						rs.getString("pw"),
//						rs.getString("mname"));
//					memberList.add(member);							// 리스트에 User 객체 저장
//				} while ((rs.next()) && (--countPerPage > 0));		
//				return memberList;							
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 반환
//		}
//		return null;
//	}
	
	/**
	 * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
	 */
	public boolean existingMember(String email_id) throws SQLException {
		String sql = "SELECT count(*) FROM member WHERE email_id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {email_id});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}

}