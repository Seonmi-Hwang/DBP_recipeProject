package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ingredient;
import model.Prefer_ingredient;

public class IngredientDAO {
	private JDBCUtil jdbcUtil = null;
	
	public IngredientDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	//����
	public int create(Ingredient ingre) throws SQLException {
		String sql = "INSERT INTO Ingredient VALUES (?, ?, ?)";		
		Object[] param = new Object[] {ingre.getRecipe_id(), 
				ingre.getIngredient_id(), ingre.getQuantity()};		
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
	
	public int create_pre(Prefer_ingredient ingre) throws SQLException {
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
	//������Ʈ
	public int update(Ingredient ingre) throws SQLException {
		String sql = "UPDATE Ingredient "
					+ "SET quantity=? "
					+ "WHERE ingredient_id=?, recipe_id=?";
		
		Object[] param = new Object[] {ingre.getQuantity(),ingre.getIngredient_id(),ingre.getRecipe_id()};				
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
	
	public int update_pre(Prefer_ingredient ingre) throws SQLException {
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
	//����
	public int remove(int ingreId, int recipe_id) throws SQLException {
		String sql = "DELETE FROM Ingredient WHERE ingredient_id=?, recipe_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {ingreId, recipe_id});	// JDBCUtil�� delete���� �Ű� ���� ����

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
	
	public int remove_pre(int ingreId, int memberId) throws SQLException {
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
	
	//ã��
	public Ingredient findIngredient(int recipId) throws SQLException {
        String sql = "SELECT ii.icategory AS category, ii.iname AS name, i.quantity AS quant"
        			+ "FROM Ingredient i, ingredient_info ii "
        			+ "WHERE i.ingredient_id=ii.ingredient_id and i.recipe_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipId});	// JDBCUtil�� query���� �Ű� ���� ����
		Ingredient ingre = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				ingre = new Ingredient(		// Ingredient ��ü�� �����Ͽ� Ŀ�´�Ƽ ������ ����
					rs.getInt("ingredient_id"),
					recipId,
					rs.getString("quant"),
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
	
	public Ingredient findIngredientname(int ingreId) throws SQLException {
        String sql = "SELECT ii.icategory AS category, ii.iname AS name "
        			+ "FROM ingredient_info ii "
        			+ "WHERE ingredient_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {ingreId});	// JDBCUtil�� query���� �Ű� ���� ����
		Ingredient ingre = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				ingre = new Ingredient(		// Ingredient ��ü�� �����Ͽ� Ŀ�´�Ƽ ������ ����
					ingreId,
					rs.getInt(""),
					rs.getString(""),
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
	
	//��ü����Ʈ
	public List<Ingredient> findIngredientList() throws SQLException {
        String sql = "SELECT i.recipe_id AS recipeid, i.ingredient_id AS ingredientid, "
        		+ "ii.icategory AS category, ii.iname AS name, i.quantity AS quantity "
        		   + "FROM Ingredient i, ingredient_info ii "
        		   + "WHERE i.ingredient_id = ii.ingredient_id "
        		   + "GROUP BY i.recipe_id, i.ingredient_id "
        		   + "ORDER BY ii.iname";        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Ingredient> ingreList = new ArrayList<Ingredient>();	// Ingredient���� ����Ʈ ����
			while (rs.next()) {
				Ingredient ingre = new Ingredient(			// Ingredient ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("ingredientid"),
						rs.getInt("recipeid"),
						rs.getString("quantity"),
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
	
	public List<Ingredient> findAllingredientList() throws SQLException {
        String sql = "SELECT ii.icategory AS category, ii.ingredient_id AS ingredientid, "
        		+ " ii.iname AS name"
     		   + "FROM ingredient_info ii "
     		   + "GROUP BY ii.icategory "
     		   + "ORDER BY ii.iname";  
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Ingredient> ingreList = new ArrayList<Ingredient>();	// Ingredient���� ����Ʈ ����
			while (rs.next()) {
				Ingredient ingre = new Ingredient(			// Ingredient ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("ingredientid"),
						rs.getInt(""),
						rs.getString(""),
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
	
	public List<Ingredient> findCategoryingredientList(String category) throws SQLException {
        String sql = "SELECT ii.ingredient_id AS ingredientid, "
        		+ " ii.iname AS name"
     		   + "FROM ingredient_info ii "
     		   + "WHERE ii.icategory = ? "
     		   + "ORDER BY ii.iname";  
		jdbcUtil.setSqlAndParameters(sql, new Object[] {category});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Ingredient> ingreList = new ArrayList<Ingredient>();	// Ingredient���� ����Ʈ ����
			while (rs.next()) {
				Ingredient ingre = new Ingredient(			// Ingredient ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("ingredientid"),
						rs.getInt(""),
						rs.getString(""),
						category,
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

