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
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}

	// ������ �߰�
	public void create(Recipe recipe, int memberId) throws SQLException {
		try {
			/* recipe_info�� �߰� */
			String sql = "INSERT INTO recipe_info (recipe_id, category_id, rname, time, result_img, hits) "
					+ "VALUES (rid_sequence.nextval, ?, ?, ?, ?, ?) ";
			Object[] param = new Object[] { recipe.getCategory_id(), recipe.getRname(), recipe.getTime(),
					recipe.getResult_img(), recipe.getHits() };
			jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �� insert���� �Ű� ���� ����
			if (jdbcUtil.executeUpdate() != 1) {
				throw new SQLException();
			}

			/* ingredient�� �߰� */
			List<Ingredient> iList = recipe.getIngredients();
			for (int i = 0; i < iList.size(); i++) {
				sql = "INSERT INTO ingredient (recipe_id, ingredient_id, quantity) "
						+ "VALUES (rid_sequence.currval, ?, ?) ";
				param = new Object[] { iList.get(i).getIngredient_id(), iList.get(i).getQuantity() };
				jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �� insert���� �Ű� ���� ����
				if (jdbcUtil.executeUpdate() != 1) {
					throw new SQLException();
				}
			}

			/* recipe_procedure�� �߰� */
			List<Procedure> pList = recipe.getProcedure();
			for (int i = 0; i < pList.size(); i++) {
				sql = "INSERT INTO recipe_procedure (recipe_id, proc_id, text, img_url) "
						+ "VALUES (rid_sequence.currval, ?, ?, ?) ";
				param = new Object[] { pList.get(i).getProc_Id(), pList.get(i).getText(), pList.get(i).getImg_url() };
				jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �� insert���� �Ű� ���� ����
				if (jdbcUtil.executeUpdate() != 1) {
					throw new SQLException();
				}
			}

			/* users_recipe�� �߰� */
			sql = "INSERT INTO users_recipe (member_id, recipe_id, createdDate) "
					+ "VALUES (?, rid_sequence.currval, ?) ";
			java.sql.Date date = new java.sql.Date(recipe.getCreatedDate().getTime());
			param = new Object[] { memberId, date };
			jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �� insert���� �Ű� ���� ����
			if (jdbcUtil.executeUpdate() != 1) {
				throw new SQLException();
			}

		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
	}

	// ������ ����
	public int update(Recipe recipe) throws SQLException {
		String sql = "UPDATE recipe_info " + "SET category_id=?, rname=?, time=?, result_img=?, hits=? "
				+ "WHERE recipe_id=?";
		Object[] param = new Object[] { recipe.getCategory_id(), recipe.getRname(), recipe.getTime(),
				recipe.getResult_img(), recipe.getHits(), recipe.getRecipe_id() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�� update���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	// !������ ���� ���� ��������� �� ��!

	// ������ ����
	public int remove(int recipe_id) throws SQLException {
		try {
			/* users_recipe���� ���� */
			String sql = "DELETE FROM users_recipe WHERE recipe_id=? ";
			Object[] param = new Object[] { recipe_id };
			jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �� insert���� �Ű� ���� ����
			if (jdbcUtil.executeUpdate() != 1) {
				throw new SQLException();
			}
			
			/* recipe_procedure���� ���� */
			sql = "DELETE FROM recipe_procedure WHERE recipe_id=? ";
			param = new Object[] { recipe_id };
			jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �� insert���� �Ű� ���� ����
			if (jdbcUtil.executeUpdate() != 1) {
				throw new SQLException();
			}
			
			/* ingredient���� ���� */
			sql = "DELETE FROM ingredient WHERE recipe_id=? ";
			param = new Object[] { recipe_id };
			jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �� insert���� �Ű� ���� ����
			if (jdbcUtil.executeUpdate() != 1) {
				throw new SQLException();
			}
			
			/* recipe_info���� ���� */
			sql = "DELETE FROM recipe_info WHERE recipe_id=?";
			param = new Object[] { recipe_id };
			jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �� insert���� �Ű� ���� ����
			if (jdbcUtil.executeUpdate() != 1) {
				throw new SQLException();
			}
			return 1;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	// �־��� recipe_id�� �ش��ϴ� ������ ������ �����ͺ��̽����� ã�Ƽ� Recipe ������ Ŭ������ �����Ͽ� ��ȯ.
	public Recipe getRecipe(int recipe_id) throws SQLException {
		String sql = "SELECT category_id, rname, time, result_img, hits " // recipe_procedure
				+ "FROM recipe_info " + "WHERE recipe_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { recipe_id }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			if (rs.next()) { // ������ ���� �߰�
				Recipe recipe = new Recipe( // Recipe ��ü�� �����Ͽ� ������ ������ ����
						recipe_id, rs.getInt("category_id"), rs.getString("rname"), rs.getInt("time"),
						rs.getString("result_img"), rs.getInt("hits"), getProcedures(recipe_id),
						getIngredientsName(recipe_id), null, getRecipeWriter(recipe_id), getCreatedDate(recipe_id));
				return recipe;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	// �־��� recipe_id�� �ش��ϴ� ������ ������ �����ͺ��̽����� ��ȸ�� Top1�� ã�Ƽ� Recipe ������ Ŭ������ �����Ͽ� ��ȯ.
	public Recipe getTopRecipe(int category_id) throws SQLException {

		String sql = "SELECT recipe_id, category_id, rname, time, result_img, hits " // recipe_procedure
				+ "FROM (SELECT recipe_id, category_id, rname, time, result_img, hits FROM recipe_info ORDER BY hits DESC) "
				+ "WHERE category_id=? " + "AND rownum = 1 ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { category_id }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			if (rs.next()) { // ������ ���� �߰�
				Recipe recipe = new Recipe( // Recipe ��ü�� �����Ͽ� ������ ������ ����
						rs.getInt("recipe_id"), category_id, rs.getString("rname"), rs.getInt("time"),
						rs.getString("result_img"), rs.getInt("hits"), null, null, null, null, null);
				return recipe;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	// �־��� category_id�� ���� �����ǵ��� ������ List<Recipe>�� ���·� ���
	public List<Recipe> getRecipeList(int category_id) throws SQLException {
		String sql = "SELECT recipe_id, rname, time, result_img, hits " // ���⼭ ingredient ����� ingredientDAO���� ���
				+ "FROM recipe_info " + "WHERE category_id=? " + "ORDER BY hits DESC ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { category_id }); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<Recipe> recipeList = new ArrayList<Recipe>(); // Recipe���� ����Ʈ ����
			while (rs.next()) {
				int recipe_id = rs.getInt("recipe_id");
				Recipe recipe = new Recipe( // Recipe ��ü�� �����Ͽ� recipe ������ ����
						recipe_id, category_id, rs.getString("rname"), rs.getInt("time"), rs.getString("result_img"),
						rs.getInt("hits"), null, null, null, null, null);
				recipeList.add(recipe); // List�� Recipe ��ü ����
			}
			return recipeList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	// �־��� email_id�� ���� �����ǵ��� ������ List<Recipe>�� ���·� ���
	public List<Recipe> getUserRecipeList(String email_id) throws SQLException {
		String sql = "SELECT r.recipe_id, rname, time, result_img, hits " // ���⼭ ingredient ����� ingredientDAO���� ���
				+ "FROM users_recipe u JOIN recipe_info r ON u.recipe_id = r.recipe_id "
				+ "WHERE r.category_id = 30 and member_id=(SELECT member_id FROM member where email_id=?)";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { email_id }); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<Recipe> recipeList = new ArrayList<Recipe>(); // Recipe���� ����Ʈ ����
			while (rs.next()) {
				int recipe_id = rs.getInt("recipe_id");
				Recipe recipe = new Recipe( // Recipe ��ü�� �����Ͽ� recipe ������ ����
						recipe_id, 30, rs.getString("rname"), rs.getInt("time"), rs.getString("result_img"),
						rs.getInt("hits"), null, null, null, null, null);
				recipeList.add(recipe); // List�� Recipe ��ü ����
			}
			return recipeList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	public String getIngredients(int recipe_id) throws SQLException {
		String sql = "SELECT DISTINCT iname " // ���⼭ ingredient ����� ingredientDAO���� ���
				+ "FROM ingredient_info info, ingredient ingr " + "WHERE info.ingredient_id = ingr.ingredient_id "
				+ "AND recipe_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { recipe_id }); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			String ingredients = "";
			while (rs.next()) {
				ingredients += rs.getString("iname") + "| ";
			}

			return ingredients;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	// �־��� category_id�� rname �κа��� ���� �����ǵ��� ������ List<Recipe>�� ���·� ���
	public List<Recipe> searchRecipeList(int category_id, String keyword) throws SQLException {
		String inputKey = "%" + keyword + "%";
		String sql = "SELECT recipe_id, rname, time, result_img, hits " // ���⼭ ingredient ����� ingredientDAO���� ���
				+ "FROM recipe_info " + "WHERE category_id IN (?, ?, ?) AND rname LIKE ?" + "ORDER BY hits DESC ";

		if (category_id == 5) { // ��ü �����ǿ��� �˻�
			jdbcUtil.setSqlAndParameters(sql, new Object[] { 10, 20, 30, inputKey }); // JDBCUtil�� query�� ����
		} else { // �� ������ ī�װ����� �˻�
			jdbcUtil.setSqlAndParameters(sql, new Object[] { category_id, 0, 0, inputKey }); // JDBCUtil�� query�� ����
		}

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<Recipe> recipeList = new ArrayList<Recipe>(); // Recipe���� ����Ʈ ����
			while (rs.next()) {
				int recipe_id = rs.getInt("recipe_id");
				Recipe recipe = new Recipe( // Recipe ��ü�� �����Ͽ� recipe ������ ����
						recipe_id, category_id, rs.getString("rname"), rs.getInt("time"), rs.getString("result_img"),
						rs.getInt("hits"), null, null, null, null, null);
				recipeList.add(recipe); // List�� Recipe ��ü ����
			}
			return recipeList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	// ��� ���� ������ ���
	public List<Integer> getRecommendRecipe(String[] ingredients) throws SQLException {
		String p = "";
		String sql = "SELECT DISTINCT ingredient.recipe_id " + "FROM ingredient,ingredient_info "
				+ "WHERE ingredient.ingredient_id=ingredient_info.ingredient_id ";
		int count = 0;
		System.out.printf("%s\n", ingredients);
		if (ingredients != null) {
			for (String i : ingredients) {
				if (i != "") {
					if (count == 0) {
						p += "'" + i + "'";
					} else {
						p += ",'" + i + "'";
					}
					count++;
				}
			}
			sql += "and ingredient_info.iname IN (" + p + ")";
			System.out.printf("%s\n", p);
		}

		jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<Integer> recipeIdList = new ArrayList<Integer>(); // Recipe���� ����Ʈ ����
			while (rs.next()) {
				recipeIdList.add(rs.getInt("recipe_id"));
				// List�� Recipe ��ü ����
			}
			System.out.printf("%s", recipeIdList);
			return recipeIdList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	// �ش� �������� �������� �������� �޼ҵ�
	public List<Procedure> getProcedures(int recipe_id) throws SQLException {
		String sql = "SELECT proc_id, text, img_url " // recipe_procedure
				+ "FROM recipe_procedure " + "WHERE recipe_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { recipe_id }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<Procedure> procList = new ArrayList<Procedure>(); // procedure�� ����Ʈ ����
			while (rs.next()) { // procedure �߰�
				Procedure procedure = new Procedure(rs.getInt("proc_id"), rs.getString("text"),
						rs.getString("img_url"));
				procList.add(procedure);
			}
			return procList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	public List<Ingredient> getIngredientsName(int recipe_id) throws SQLException {
		String sql = "SELECT iname, quantity " + "FROM ingredient igre, ingredient_info info "
				+ "WHERE igre.ingredient_id = info.ingredient_id " + "AND recipe_id = ? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { recipe_id }); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
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
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	public String getRecipeWriter(int recipe_id) throws SQLException {
		String sql = "SELECT mname " + "FROM users_recipe u, member m " + "WHERE u.member_id = m.member_id "
				+ "AND recipe_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { recipe_id });

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			String writer = "";
			if (rs.next()) {
				writer = rs.getString("mname");
			}
			return writer;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return "";
	}

	public int getRecipeWriterId(int recipe_id) throws SQLException {
		String sql = "SELECT member_id " + "FROM users_recipe " + "WHERE recipe_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { recipe_id });

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			int writerId = 1;
			while (rs.next()) {
				writerId = rs.getInt("member_id");
			}
			return writerId;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return 1; // ���޾ƿ��� �ۼ��� = ������(1��)���� �ʱ�ȭ
	}

	public Date getCreatedDate(int recipe_id) throws SQLException {
		String sql = "SELECT createdDate " + "FROM users_recipe " + "WHERE recipe_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { recipe_id });

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			Date createdDate = null;
			while (rs.next()) {
				createdDate = rs.getDate("createdDate");
			}
			return createdDate;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

}
