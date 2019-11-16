package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Procedure;
import model.Recipe;

public class RecipeDAO {
private JDBCUtil jdbcUtil = null;
	
	public RecipeDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	// ������ �߰�
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

	// ������ ����
	public int update(Recipe recipe) throws SQLException {
		String sql = "UPDATE recipe_info "
					+ "SET category_id=?, rname=?, time=?, result_img=?, hits=? "
					+ "WHERE recipe_id=?";
		Object[] param = new Object[] {recipe.getCategory_id(), 
				recipe.getRname(), recipe.getTime(), recipe.getResult_img(), recipe.getHits(), recipe.getRecipe_id()};				
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

	// ������ ����
	public int remove(int recipeId) throws SQLException {
		String sql = "DELETE FROM recipe_info WHERE recipe_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipeId});	// JDBCUtil�� delete���� �Ű� ���� ����

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

	
	// �־��� recipe_id�� �ش��ϴ� ������ ������ �����ͺ��̽����� ã�Ƽ� Recipe ������ Ŭ������ �����Ͽ� ��ȯ.
	public Recipe getRecipe(int recipeId) throws SQLException {
        String sql = "SELECT category_id, rname, time, result_img, hits " // recipe_procedure
        			+ "FROM recipe_info "
        			+ "WHERE recipe_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipeId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// ������ ���� �߰�
				Recipe recipe = new Recipe (		// Recipe ��ü�� �����Ͽ� ������ ������ ����
					recipeId,
					rs.getInt("category_id"),
					rs.getString("rname"),
					rs.getString("time"),
					rs.getString("result_img"),
					rs.getInt("hits"),
					getProcedures(recipeId),
					getIngredientsName(recipeId));
				return recipe;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	// �־��� category_id�� ���� �����ǵ��� ������ List<Recipe>�� ���·� ���
	public List<Recipe> getRecipeList() throws SQLException {
        String sql = "SELECT result_img, rname, time, hits " // ���⼭ ingredient ����� ingredientDAO���� ���
        		   + "FROM recipe_info"	
        		   + "WHERE category_id=?"
        		   + "ORDER BY hits DESC";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Recipe> recipeList = new ArrayList<Recipe>();	// Recipe���� ����Ʈ ����
			while (rs.next()) {
				int recipeId = rs.getInt("recipe_id");
				Recipe recipe = new Recipe (		// Recipe ��ü�� �����Ͽ� recipe ������ ����
					recipeId,
					rs.getInt("category_id"),
					rs.getString("rname"),
					rs.getString("time"),
					rs.getString("result_img"),
					rs.getInt("hits"),
					null,
					getIngredientsName(recipeId));	
				recipeList.add(recipe);				// List��  Recipe ��ü ����
			}		
			return recipeList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	// ��� ���� ������ ���
	public List<Integer> getRecommendRecipe(List<Integer> ingredients) {
		String sql = "SELECT DISTINCT recipe_id "
					+ "FROM ingredient"
					+ "WHERE ingredient_id IN (?)";
		
		String parameter = "";
		for (int i = 0; i < ingredients.size(); i++) {
			parameter += String.valueOf(ingredients.get(i));
			
			if (i != ingredients.size() - 1) {
				parameter += ", ";
			}
		}
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {});	// JDBCUtil�� query���� �Ű� ���� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Integer> recipeIdList = new ArrayList<Integer>();	// Recipe���� ����Ʈ ����
			while (rs.next()) {
				recipeIdList.add(rs.getInt("recipe_id"));				// List��  Recipe ��ü ����
			}		
			return recipeIdList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}

	// �ش� �������� �������� �������� �޼ҵ� 
	public List<Procedure> getProcedures(int recipeId) {
        String sql = "SELECT proc_id, text, img_url " // recipe_procedure
    			+ "FROM recipe_procedure "
    			+ "WHERE recipe_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipeId});	// JDBCUtil�� query���� �Ű� ���� ����
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			List<Procedure> procList = new ArrayList<Procedure>();	// procedure�� ����Ʈ ����
			while (rs.next()) {					// procedure �߰�
				Procedure procedure = new Procedure (
					rs.getInt("proc_id"),
					rs.getString("text"),
					rs.getString("img_url"));
					procList.add(procedure);
			}
			return procList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
		
	public List<String> getIngredientsName(int recipeId) {
        String sql = "SELECT iname, quantity "
				+ "FROM ingredient igre, ingredients_info info"
				+ "WHERE igre.ingredient_id = info.ingredient_id "
				+ "AND recipe_id = ?";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<String> ingredientList = new ArrayList<String>();
			while (rs.next()) {
				String ingredient = rs.getString("iname") + rs.getString("quantity");						
				ingredientList.add(ingredient);
			}		
			return ingredientList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
}