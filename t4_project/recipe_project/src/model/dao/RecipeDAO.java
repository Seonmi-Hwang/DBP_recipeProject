package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Recipe;

public class RecipeDAO {
private JDBCUtil jdbcUtil = null;
	
	public RecipeDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	// ������ ���� ���̺� ���ο� ������ ����.
	public int create(Recipe recipe) throws SQLException {
		String sql = "INSERT INTO recipe_info (recipe_id, category_id, rname, time, result_img, hits) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {recipe.getRecipe_id(), recipe.getCategory_id(), 
				recipe.getRname(), recipe.getTime(), recipe.getResult_img(), recipe.getHits()};				
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

	// .. ���� �� ���� ����

	// �־��� ����� ID�� �ش��ϴ� ����� ������ �����ͺ��̽����� ã�� User ������ Ŭ������ �����Ͽ� ��ȯ.
	public Recipe findRecipe(String userId) throws SQLException {
//        String sql = "SELECT password, name, email, phone "
//        			+ "FROM USERINFO "
//        			+ "WHERE userid=? ";              
//		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil�� query���� �Ű� ���� ����
//
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();		// query ����
//			if (rs.next()) {						// �л� ���� �߰�
//				User user = new User(		// User ��ü�� �����Ͽ� �л� ������ ����
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
//			jdbcUtil.close();		// resource ��ȯ
//		}
		return null;
	}

	/**
	 * ��ü ����� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<Recipe> findRecipeList() throws SQLException {
//        String sql = "SELECT userId, password, name, email, phone " 
//        		   + "FROM USERINFO "
//        		   + "ORDER BY userId";
//		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
//					
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
//			List<User> userList = new ArrayList<User>();	// User���� ����Ʈ ����
//			while (rs.next()) {
//				User user = new User(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
//					rs.getString("userId"),
//					rs.getString("password"),
//					rs.getString("name"),
//					rs.getString("email"),
//					rs.getString("phone"));	
//				userList.add(user);				// List�� User ��ü ����
//			}		
//			return userList;					
//			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource ��ȯ
//		}
		return null;
	}
	
	/**
	 * ��ü ����� ������ �˻��� �� ���� �������� �������� ����� ����� ���� �̿��Ͽ�
	 * �ش��ϴ� ����� �������� List�� �����Ͽ� ��ȯ.
	 * ... ����
	 */

//	// �־��� ����� ID�� �ش��ϴ� ����ڰ� �����ϴ��� �˻� 
//	public boolean existingUser(String userId) throws SQLException {
//		String sql = "SELECT count(*) FROM USERINFO WHERE userid=?";      
//		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil�� query���� �Ű� ���� ����
//
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();		// query ����
//			if (rs.next()) {
//				int count = rs.getInt(1);
//				return (count == 1 ? true : false);
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource ��ȯ
//		}
//		return false;
//	}


}