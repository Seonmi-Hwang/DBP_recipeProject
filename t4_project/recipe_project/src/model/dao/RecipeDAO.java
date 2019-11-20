package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Ingredient;
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
					+ "VALUES (rid_sequence.nextval, ?, ?, ?, ?, ?) ";		
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
	
	// !������ ���� ���� ��������� �� ��!
	

	// ������ ����
	public int remove(int recipe_id) throws SQLException {
		String sql = "DELETE FROM recipe_info WHERE recipe_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipe_id});	// JDBCUtil�� delete���� �Ű� ���� ����

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
	public Recipe getRecipe(int recipe_id) throws SQLException {
        String sql = "SELECT category_id, rname, time, result_img, hits " // recipe_procedure
        			+ "FROM recipe_info "
        			+ "WHERE recipe_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipe_id});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// ������ ���� �߰�
				Recipe recipe = new Recipe (		// Recipe ��ü�� �����Ͽ� ������ ������ ����
					recipe_id,
					rs.getInt("category_id"),
					rs.getString("rname"),
					rs.getString("time"),
					rs.getString("result_img"),
					rs.getInt("hits"),
					getProcedures(recipe_id),
					getIngredientsName(recipe_id),
					null,
					getRecipeWriter(recipe_id),
					getCreatedDate(recipe_id));
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
	public List<Recipe> getRecipeList(int category_id) throws SQLException {
        String sql = "SELECT recipe_id, rname, time, result_img, hits " // ���⼭ ingredient ����� ingredientDAO���� ���
        		   + "FROM recipe_info "	
        		   + "WHERE category_id=? "
        		   + "ORDER BY hits DESC ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {category_id});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Recipe> recipeList = new ArrayList<Recipe>();	// Recipe���� ����Ʈ ����
			while (rs.next()) {
				int recipe_id = rs.getInt("recipe_id");
				Recipe recipe = new Recipe (		// Recipe ��ü�� �����Ͽ� recipe ������ ����
					recipe_id,
					category_id,
					rs.getString("rname"),
					rs.getString("time"),
					rs.getString("result_img"),
					rs.getInt("hits"),
					null,
					null,
					null,
					null,
					null);	
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
	
	// �־��� email_id�� ���� �����ǵ��� ������ List<Recipe>�� ���·� ���
	public List<Recipe> getUserRecipeList(String email_id) throws SQLException {
        String sql = "SELECT r.recipe_id, rname, time, result_img, hits " // ���⼭ ingredient ����� ingredientDAO���� ���
        		   + "FROM users_recipe u JOIN recipe_info r ON u.recipe_id = r.recipe_id "	
        		   + "WHERE r.category_id = 30 and member_id=(SELECT member_id FROM member where email_id=?)";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {email_id});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Recipe> recipeList = new ArrayList<Recipe>();	// Recipe���� ����Ʈ ����
			while (rs.next()) {
				int recipe_id = rs.getInt("recipe_id");
				Recipe recipe = new Recipe (		// Recipe ��ü�� �����Ͽ� recipe ������ ����
					recipe_id,
					30,
					rs.getString("rname"),
					rs.getString("time"),
					rs.getString("result_img"),
					rs.getInt("hits"),
					null,
					null,
					null,
					null,
					null);	
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
	
	public String getIngredients(int recipe_id) throws SQLException {
	       String sql = "SELECT DISTINCT iname " // ���⼭ ingredient ����� ingredientDAO���� ���
        		   + "FROM ingredient_info info, ingredient ingr "
        		   + "WHERE info.ingredient_id = ingr.ingredient_id "
        		   + "AND recipe_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipe_id});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			String ingredients = "";
			while (rs.next()) {
				ingredients += rs.getString("iname") + "| ";
			}
			
			
			return ingredients;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	// �־��� category_id�� rname �κа��� ���� �����ǵ��� ������ List<Recipe>�� ���·� ���
	public List<Recipe> searchRecipeList(int category_id, String keyword) throws SQLException {
		String inputKey = "%" + keyword + "%";
        String sql = "SELECT recipe_id, rname, time, result_img, hits " // ���⼭ ingredient ����� ingredientDAO���� ���
        		   + "FROM recipe_info "	
        		   + "WHERE category_id IN (?, ?, ?) AND rname LIKE ?"
        		   + "ORDER BY hits DESC ";
        
        if (category_id == 5) { // ��ü �����ǿ��� �˻�
    		jdbcUtil.setSqlAndParameters(sql, new Object[] {10, 20, 30, inputKey});		// JDBCUtil�� query�� ����
        } else { // �� ������ ī�װ����� �˻�
    		jdbcUtil.setSqlAndParameters(sql, new Object[] {category_id, 0, 0, inputKey});		// JDBCUtil�� query�� ����
        }
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Recipe> recipeList = new ArrayList<Recipe>();	// Recipe���� ����Ʈ ����
			while (rs.next()) {
				int recipe_id = rs.getInt("recipe_id");
				Recipe recipe = new Recipe (		// Recipe ��ü�� �����Ͽ� recipe ������ ����
					recipe_id,
					category_id,
					rs.getString("rname"),
					rs.getString("time"),
					rs.getString("result_img"),
					rs.getInt("hits"),
					null,
					null,
					null,
					null,
					null);	
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
	public List<Integer> getRecommendRecipe(List<Integer> ingredients) throws SQLException {
		String sql = "SELECT DISTINCT recipe_id "
					+ "FROM ingredient "
					+ "WHERE ingredient_id IN (?) ";
		
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
	public List<Procedure> getProcedures(int recipe_id) throws SQLException {
        String sql = "SELECT proc_id, text, img_url " // recipe_procedure
    			+ "FROM recipe_procedure "
    			+ "WHERE recipe_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipe_id});	// JDBCUtil�� query���� �Ű� ���� ����
	
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
		
	public List<Ingredient> getIngredientsName(int recipe_id) throws SQLException {
        String sql = "SELECT iname, quantity "
				+ "FROM ingredient igre, ingredient_info info "
				+ "WHERE igre.ingredient_id = info.ingredient_id "
				+ "AND recipe_id = ? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipe_id});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Ingredient> ingredientList = new ArrayList<>();
			while (rs.next()) {
				Ingredient ingredient = new Ingredient();
				ingredient.setIname(rs.getString("iname"));
				ingredient.setQuantity(rs.getString("quantity"));						
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
	
	public String getRecipeWriter(int recipe_id) throws SQLException {
		 String sql = "SELECT mname "
					+ "FROM users_recipe u, member m "
					+ "WHERE u.member_id = m.member_id "
					+ "AND recipe_id = ?";
			jdbcUtil.setSqlAndParameters(sql, new Object[] {recipe_id});
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();			// query ����			
				String writer = "";
				if (rs.next()) {
					writer = rs.getString("mname");
				}					
				return writer;
				
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource ��ȯ
			}
			return "";
	}
	
	public int getRecipeWriterId(int recipe_id) throws SQLException {
		 String sql = "SELECT member_id "
					+ "FROM users_recipe "
					+ "WHERE recipe_id = ?";
			jdbcUtil.setSqlAndParameters(sql, new Object[] {recipe_id});
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();			// query ����			
				int writerId = 1;
				while (rs.next()) {
					writerId = rs.getInt("member_id");
				}		
				return writerId;					
				
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource ��ȯ
			}
			return 1;	// ���޾ƿ��� �ۼ��� = ������(1��)���� �ʱ�ȭ
	}
	
	public Date getCreatedDate(int recipe_id) throws SQLException {
		 String sql = "SELECT createdDate "
					+ "FROM users_recipe "
					+ "WHERE recipe_id = ?";
			jdbcUtil.setSqlAndParameters(sql, new Object[] {recipe_id});
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();			// query ����			
				Date createdDate = null;
				while (rs.next()) {
					createdDate = rs.getDate("createdDate");
				}		
				return createdDate;					
				
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource ��ȯ
			}
			return null;
	}
	
	
	
}