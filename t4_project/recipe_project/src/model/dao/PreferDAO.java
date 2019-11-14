package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Prefer_ingredient;

public class PreferDAO {
	private JDBCUtil jdbcUtil = null;
	
	public PreferDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	public int create(Prefer_ingredient ingre) throws SQLException {
		String sql = "INSERT INTO Prefer_Ingredient VALUES (?, ?, ?)";		
		Object[] param = new Object[] {ingre.getMember_id(), 
				ingre.getIngredient_id(), ingre.getPrefer()};
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
	

	public int update(Prefer_ingredient ingre) throws SQLException {
		String sql = "UPDATE Prefer_ingredient "
					+ "SET prefer=? "
					+ "WHERE ingredient_id=?, member_id=?";
		
		Object[] param = new Object[] {ingre.getPrefer(),
				ingre.getIngredient_id(),ingre.getMember_id()};				
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
	
	public int remove(int ingreId, int memberId) throws SQLException {
		String sql = "DELETE FROM Prefer_ingredient WHERE ingredient_id=?, member_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {ingreId, memberId});	// JDBCUtil�� delete���� �Ű� ���� ����

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
	
	public Prefer_ingredient findPreingredient(int ingreId,int memberId) throws SQLException {
        String sql = "SELECT ii.icategory AS category, ii.iname AS name, i.prefer AS prefer"
        			+ "FROM Prefer_ingredient i, ingredient_info ii "
        			+ "WHERE i.ingredient_id=ii.ingredient_id and i.ingredient_id=? and i.member_id=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {ingreId,memberId});	// JDBCUtil�� query���� �Ű� ���� ����
		Prefer_ingredient ingre = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				ingre = new Prefer_ingredient(		// Ingredient ��ü�� �����Ͽ� Ŀ�´�Ƽ ������ ����
					ingreId,
					memberId,
					rs.getString("prefer"),
					rs.getString("category"),
					rs.getString("name"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return ingre;
	}
	
	
	public List<Prefer_ingredient> findPreingredientList() throws SQLException {
        String sql = "SELECT i.member_id AS memberid, i.ingredient_id AS ingredientid, "
        		+ "ii.icategory AS category, ii.iname AS name, i.prefer AS prefer "
        		   + "FROM prefer_ingredient i, ingredient_info ii "
        		   + "WHERE i.ingredient_id = ii.ingredient_id "
        		   + "GROUP BY i.recipe_id, i.ingredient_id "
        		   + "ORDER BY ii.iname";        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Prefer_ingredient> ingreList = new ArrayList<Prefer_ingredient>();	// Ingredient���� ����Ʈ ����
			while (rs.next()) {
				Prefer_ingredient ingre = new Prefer_ingredient(			// Ingredient ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("ingredientid"),
						rs.getInt("memberid"),
						rs.getString("prefer"),
						rs.getString("category"),
						rs.getString("name"));
				ingreList.add(ingre);				// List�� Ingredient ��ü ����
			}		
			return ingreList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
}
