package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Recipe;

public class RecipeDAO {
private JDBCUtil jdbcUtil = null;
	
	public RecipeDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	// 레시피 관리 테이블에 새로운 레시피 생성.
	public int create(Recipe recipe) throws SQLException {
		String sql = "INSERT INTO recipe_info (recipe_id, category_id, rname, time, result_img, hits) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {recipe.getRecipe_id(), recipe.getCategory_id(), 
				recipe.getRname(), recipe.getTime(), recipe.getResult_img(), recipe.getHits()};				
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

	// .. 수정 및 삭제 생략

	// 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 User 도메인 클래스에 저장하여 반환.
	public Recipe findRecipe(String userId) throws SQLException {
//        String sql = "SELECT password, name, email, phone "
//        			+ "FROM USERINFO "
//        			+ "WHERE userid=? ";              
//		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 query문과 매개 변수 설정
//
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
//			if (rs.next()) {						// 학생 정보 발견
//				User user = new User(		// User 객체를 생성하여 학생 정보를 저장
//					userId,
//					rs.getString("password"),
//					rs.getString("name"),
//					rs.getString("email"),
//					rs.getString("phone"));
//				return user;
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 반환
//		}
		return null;
	}

	/**
	 * 전체 사용자 정보를 검색하여 List에 저장 및 반환
	 */
	public List<Recipe> findRecipeList() throws SQLException {
//        String sql = "SELECT userId, password, name, email, phone " 
//        		   + "FROM USERINFO "
//        		   + "ORDER BY userId";
//		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
//					
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
//			List<User> userList = new ArrayList<User>();	// User들의 리스트 생성
//			while (rs.next()) {
//				User user = new User(			// User 객체를 생성하여 현재 행의 정보를 저장
//					rs.getString("userId"),
//					rs.getString("password"),
//					rs.getString("name"),
//					rs.getString("email"),
//					rs.getString("phone"));	
//				userList.add(user);				// List에 User 객체 저장
//			}		
//			return userList;					
//			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 반환
//		}
		return null;
	}
	
	/**
	 * 전체 사용자 정보를 검색한 후 현재 페이지와 페이지당 출력할 사용자 수를 이용하여
	 * 해당하는 사용자 정보만을 List에 저장하여 반환.
	 * ... 생략
	 */

//	// 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
//	public boolean existingUser(String userId) throws SQLException {
//		String sql = "SELECT count(*) FROM USERINFO WHERE userid=?";      
//		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 query문과 매개 변수 설정
//
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
//			if (rs.next()) {
//				int count = rs.getInt(1);
//				return (count == 1 ? true : false);
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 반환
//		}
//		return false;
//	}


}