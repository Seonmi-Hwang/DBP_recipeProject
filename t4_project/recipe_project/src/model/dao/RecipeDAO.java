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
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	// 레시피 추가
	public int create(Recipe recipe, int memberId) throws SQLException {
		try {
			/* recipe_info에 추가 */
			String sql = "INSERT INTO recipe_info (recipe_id, category_id, rname, time, result_img, hits) "
					+ "VALUES (rid_sequence.nextval, ?, ?, ?, ?, ?) ";
			Object[] param = new Object[] { recipe.getCategory_id(), recipe.getRname(), recipe.getTime(),
					recipe.getResult_img(), recipe.getHits() };
			jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정
			if (jdbcUtil.executeUpdate() != 1) {
				throw new SQLException();
			}

			/* ingredient에 추가 */
			List<Ingredient> iList = recipe.getIngredients();
			for (int i = 0; i < iList.size(); i++) {
				sql = "INSERT INTO ingredient (recipe_id, ingredient_id, quantity) "
						+ "VALUES (rid_sequence.currval, ?, ?) ";
				param = new Object[] { iList.get(i).getIngredient_id(), iList.get(i).getQuantity() };
				jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정
				if (jdbcUtil.executeUpdate() != 1) {
					throw new SQLException();
				}
			}

			/* recipe_procedure에 추가 */
			List<Procedure> pList = recipe.getProcedure();
			for (int i = 0; i < pList.size(); i++) {
				sql = "INSERT INTO recipe_procedure (recipe_id, proc_id, text, img_url) "
						+ "VALUES (rid_sequence.currval, ?, ?, ?) ";
				param = new Object[] { pList.get(i).getProc_Id(), pList.get(i).getText(), pList.get(i).getImg_url() };
				jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정
				if (jdbcUtil.executeUpdate() != 1) {
					throw new SQLException();
				}
			}

			/* users_recipe에 추가 */
			sql = "INSERT INTO users_recipe (member_id, recipe_id, createdDate) "
					+ "VALUES (?, rid_sequence.currval, ?) ";
			java.sql.Date date = new java.sql.Date(recipe.getCreatedDate().getTime());
			param = new Object[] { memberId, date };
			jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정
			if (jdbcUtil.executeUpdate() != 1) {
				throw new SQLException();
			}

			/* 현재 레시피 아이디 select */
			sql = "SELECT rid_sequence.currval AS recipe_id FROM DUAL ";
			param = new Object[] {};
			jdbcUtil.setSqlAndParameters(sql, param);
			ResultSet rs = jdbcUtil.executeQuery();

			if (rs.next()) {
				return rs.getInt("recipe_id");
			}
			return 1;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	// 레시피 수정
	public int update(Recipe recipe) throws SQLException {
		try {
			/* recipe_info에 수정 */
			String sql = "UPDATE recipe_info SET category_id = ?, rname = ?, time = ?, result_img = ?, hits = ? "
					+ "WHERE recipe_id = ?";
			Object[] param = new Object[] { recipe.getCategory_id(), recipe.getRname(), recipe.getTime(),
					recipe.getResult_img(), recipe.getHits(), recipe.getRecipe_id() };
			jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();

			/* ingredient에 수정 */
//			List<Ingredient> iList = recipe.getIngredients();
//			for (int i = 0; i < iList.size(); i++) {
//				sql = "UPDATE ingredient SET quantity = ? " + "WHERE recipe_id = ? and ingredient_id = ?";
//				param = new Object[] { iList.get(i).getQuantity(), recipe.getRecipe_id(), iList.get(i).getIngredient_id() };
//				jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정
//				jdbcUtil.executeUpdate();
//			}
			
			sql = "DELETE FROM ingredient WHERE recipe_id = ?";
			param = new Object[] { recipe.getRecipe_id() };
			jdbcUtil.setSqlAndParameters(sql, param);
			jdbcUtil.executeUpdate();
			
			List<Ingredient> iList = recipe.getIngredients();
			for (int i = 0; i < iList.size(); i++) {
				sql = "INSERT INTO ingredient (recipe_id, ingredient_id, quantity) "
						+ "VALUES (?, ?, ?) ";
				param = new Object[] { recipe.getRecipe_id(), iList.get(i).getIngredient_id(), iList.get(i).getQuantity() };
				jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정
				if (jdbcUtil.executeUpdate() != 1) {
					throw new SQLException();
				}
			}			

			/* recipe_procedure에 수정 */
			List<Procedure> pList = recipe.getProcedure();
			int i; //밑에서 삭제
			for (i = 0; i < pList.size(); i++) {
				sql = "UPDATE recipe_procedure SET text=?, img_url=? " + "WHERE recipe_id=? and proc_id=?";
				param = new Object[] { pList.get(i).getText(), pList.get(i).getImg_url(),
						recipe.getRecipe_id(), (i + 1)};
				jdbcUtil.setSqlAndParameters(sql, param);
				jdbcUtil.executeUpdate();
			}
			
			sql = "DELETE FROM recipe_procedure WHERE recipe_id = ? and proc_id > ?";
			param = new Object[] { recipe.getRecipe_id(), i };
			jdbcUtil.setSqlAndParameters(sql, param);
			jdbcUtil.executeUpdate();
			
			

			/* users_recipe에 수정은 안하는걸로? update 날짜로 수정 해야될까? */
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	// !레시피 조리 과정 변경해줘야 할 듯!

	// 레시피 삭제
	public int remove(int recipe_id) throws SQLException {
		try {
			/* users_recipe에서 삭제 */
			String sql = "DELETE FROM users_recipe WHERE recipe_id=? ";
			Object[] param = new Object[] { recipe_id };
			jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정
			if (jdbcUtil.executeUpdate() != 1) {
				throw new SQLException();
			}

			/* recipe_procedure에서 삭제 */
			sql = "DELETE FROM recipe_procedure WHERE recipe_id=? ";
			param = new Object[] { recipe_id };
			jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정
			if (jdbcUtil.executeUpdate() == 0) {
				throw new SQLException();
			}

			/* ingredient에서 삭제 */
			sql = "DELETE FROM ingredient WHERE recipe_id=? ";
			param = new Object[] { recipe_id };
			jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정
			if (jdbcUtil.executeUpdate() == 0) {
				throw new SQLException();
			}
			
			/* recipe_info에서 삭제 */
			sql = "DELETE FROM recipe_info WHERE recipe_id=?";
			param = new Object[] { recipe_id };
			jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정
			if (jdbcUtil.executeUpdate() != 1) {
				throw new SQLException();
			}

			return 1;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0; // 리턴 왜하는지 모르겠음
	}

	// 주어진 recipe_id에 해당하는 레시피 정보를 데이터베이스에서 찾아서 Recipe 도메인 클래스에 저장하여 반환.
	public Recipe getRecipe(int recipe_id) throws SQLException {

		String sql = "SELECT category_id, rname, time,  "
				+ "NVL(result_img,'https://image.flaticon.com/icons/svg/1609/1609793.svg') AS result_img, hits  "
				+ " FROM recipe_info " + "WHERE recipe_id=? ";   // recipe_procedure
		jdbcUtil.setSqlAndParameters(sql, new Object[] { recipe_id }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if (rs.next()) { // 레시피 정보 발견
				Recipe recipe = new Recipe( // Recipe 객체를 생성하여 레시피 정보를 저장
						recipe_id, rs.getInt("category_id"), rs.getString("rname"), rs.getInt("time"),
						rs.getString("result_img"), rs.getInt("hits"), getProcedures(recipe_id),
						getIngredients(recipe_id), null, getRecipeWriter(recipe_id), getCreatedDate(recipe_id));
				return recipe;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	// 주어진 recipe_id에 해당하는 레시피 정보를 데이터베이스에서 조회수 Top1을 찾아서 Recipe 도메인 클래스에 저장하여 반환.
	public Recipe getTopRecipe(int category_id) throws SQLException {

		String sql = "SELECT recipe_id, category_id, rname, time, NVL(result_img,'https://image.flaticon.com/icons/svg/1609/1609793.svg') AS result_img, hits " // recipe_procedure
				+ "FROM (SELECT recipe_id, category_id, rname, time, result_img, hits FROM recipe_info ORDER BY hits DESC) "
				+ "WHERE category_id=? " + "AND rownum = 1 ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { category_id }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if (rs.next()) { // 레시피 정보 발견
				int recipe_id = rs.getInt("recipe_id");
				Recipe recipe = new Recipe( // Recipe 객체를 생성하여 레시피 정보를 저장
						recipe_id, category_id, rs.getString("rname"), rs.getInt("time"),
						rs.getString("result_img"), rs.getInt("hits"), null, null, null, null, null);
				return recipe;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	// 주어진 category_id에 따라 레시피들의 정보를 List<Recipe>의 형태로 출력
	public List<Recipe> getRecipeList(int category_id) throws SQLException {
		String sql = "SELECT recipe_id, rname, time, NVL(result_img,'https://image.flaticon.com/icons/svg/1609/1609793.svg') AS result_img, hits " // 여기서 ingredient 목록을 ingredientDAO에서 출력
				+ "FROM recipe_info " + "WHERE category_id=? " + "ORDER BY hits DESC ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { category_id }); // JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Recipe> recipeList = new ArrayList<Recipe>(); // Recipe들의 리스트 생성
			while (rs.next()) {
				int recipe_id = rs.getInt("recipe_id");
				Recipe recipe = new Recipe( // Recipe 객체를 생성하여 recipe 정보를 저장
						recipe_id, category_id, rs.getString("rname"), rs.getInt("time"), rs.getString("result_img"),
						rs.getInt("hits"), null, null, null, null, null);
				recipeList.add(recipe); // List에 Recipe 객체 저장
			}
			return recipeList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	// 주어진 email_id에 따라 레시피들의 정보를 List<Recipe>의 형태로 출력
	public List<Recipe> getUserRecipeList(String email_id) throws SQLException {
		String sql = "SELECT r.recipe_id, rname, time, NVL(result_img,'https://image.flaticon.com/icons/svg/1609/1609793.svg') AS result_img, hits " // 여기서 ingredient 목록을 ingredientDAO에서 출력
				+ "FROM users_recipe u JOIN recipe_info r ON u.recipe_id = r.recipe_id "
				+ "WHERE r.category_id = 30 and member_id=(SELECT member_id FROM member where email_id=?)";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { email_id }); // JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Recipe> recipeList = new ArrayList<Recipe>(); // Recipe들의 리스트 생성
			while (rs.next()) {
				int recipe_id = rs.getInt("recipe_id");
				Recipe recipe = new Recipe( // Recipe 객체를 생성하여 recipe 정보를 저장
						recipe_id, 30, rs.getString("rname"), rs.getInt("time"), rs.getString("result_img"),
						rs.getInt("hits"), null, null, null, null, null);
				recipeList.add(recipe); // List에 Recipe 객체 저장
			}
			return recipeList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	/* list.jsp에서 재료들 이름의 배열을 나타내기 위한 메소드 */
	public String getIngredientsName(int recipe_id) throws SQLException {
		String sql = "SELECT DISTINCT iname " // 여기서 ingredient 목록을 ingredientDAO에서 출력
				+ "FROM ingredient_info info, ingredient ingr " + "WHERE info.ingredient_id = ingr.ingredient_id "
				+ "AND recipe_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { recipe_id }); // JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			String ingredients = "";
			int count = 0;

			while (rs.next()) {
				ingredients += rs.getString("iname") + " | ";
				count++;
				if(count==4) {
					ingredients += "<br>";

					count=0;
				}
			}
			return ingredients;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	// 주어진 category_id와 rname 부분값에 따라 레시피들의 정보를 List<Recipe>의 형태로 출력
	public List<Recipe> searchRecipeList(int category_id, String keyword) throws SQLException {
		String inputKey = "%" + keyword + "%";
		String sql = "SELECT recipe_id, rname, time, NVL(result_img,'https://image.flaticon.com/icons/svg/1609/1609793.svg') AS result_img, hits " // 여기서 ingredient 목록을 ingredientDAO에서 출력
				+ "FROM recipe_info " + "WHERE category_id IN (?, ?, ?) AND rname LIKE ?" + "ORDER BY hits DESC ";

		if (category_id == 5) { // 전체 레시피에서 검색
			jdbcUtil.setSqlAndParameters(sql, new Object[] { 10, 20, 30, inputKey }); // JDBCUtil에 query문 설정
		} else { // 각 레시피 카테고리에서 검색
			jdbcUtil.setSqlAndParameters(sql, new Object[] { category_id, 0, 0, inputKey }); // JDBCUtil에 query문 설정
		}

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Recipe> recipeList = new ArrayList<Recipe>(); // Recipe들의 리스트 생성
			while (rs.next()) {
				int recipe_id = rs.getInt("recipe_id");
				Recipe recipe = new Recipe( // Recipe 객체를 생성하여 recipe 정보를 저장
						recipe_id, category_id, rs.getString("rname"), rs.getInt("time"), rs.getString("result_img"),
						rs.getInt("hits"), null, null, null, null, null);
				recipeList.add(recipe); // List에 Recipe 객체 저장
			}
			return recipeList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	// 재료 맞춤 레시피 출력
	public List<Integer> getRecommendRecipe(String[] ingredients, int nonPrefer) throws SQLException {
		String p = "'a'";
		String sql = "SELECT DISTINCT recipe_id " + "FROM ingredient,ingredient_info "
				+ "WHERE ingredient.ingredient_id=ingredient_info.ingredient_id ";
		int count = 0;
		if (ingredients != null) {
			for (String i : ingredients) {
				if (i != "") {
					if (count == 0) {
						p = "'" + i + "'";
					} else {
						p += ",'" + i + "'";
					}
					count++;
				}
				
			}
			System.out.printf("%s\n", p);
			if (p != "")
				sql += "and ingredient_info.iname IN (" + p + ") "; 	
		}
		sql = sql + "minus SELECT distinct recipe_id FROM ingredient,ingredient_info "
				+ "WHERE ingredient.ingredient_id=ingredient_info.ingredient_id and ingredient_info.ingredient_id = ?";
				
		Object[] param = new Object[] {nonPrefer};
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Integer> recipeIdList = new ArrayList<Integer>(); // Recipe들의 리스트 생성
			while (rs.next()) {
				recipeIdList.add(rs.getInt("recipe_id"));
				// List에 Recipe 객체 저장
			}
			System.out.printf("%s", recipeIdList);
			return recipeIdList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	// 해당 레시피의 순서들을 가져오는 메소드
	public List<Procedure> getProcedures(int recipe_id) throws SQLException {
		String sql = "SELECT proc_id, text, img_url " // recipe_procedure
				+ "FROM recipe_procedure " + "WHERE recipe_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { recipe_id }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Procedure> procList = new ArrayList<Procedure>(); // procedure의 리스트 생성
			while (rs.next()) { // procedure 발견
				Procedure procedure = new Procedure(rs.getInt("proc_id"), rs.getString("text"),
						rs.getString("img_url"));
				procList.add(procedure);
			}
			return procList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	/* ingredient 객체(이름, 양)를 가져오기 위한 메소드  */
	public List<Ingredient> getIngredients(int recipe_id) throws SQLException {
		String sql = "SELECT iname, quantity " + "FROM ingredient igre, ingredient_info info "
				+ "WHERE igre.ingredient_id = info.ingredient_id " + "AND recipe_id = ? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { recipe_id }); // JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
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
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	public String getRecipeWriter(int recipe_id) throws SQLException {
		String sql = "SELECT mname " + "FROM users_recipe u, member m " + "WHERE u.member_id = m.member_id "
				+ "AND recipe_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { recipe_id });

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			String writer = "";
			if (rs.next()) {
				writer = rs.getString("mname");
			}
			return writer;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return "";
	}

	public int getRecipeWriterId(int recipe_id) throws SQLException {
		String sql = "SELECT member_id " + "FROM users_recipe " + "WHERE recipe_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { recipe_id });

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			int writerId = 1;
			while (rs.next()) {
				writerId = rs.getInt("member_id");
			}
			return writerId;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return 1; // 못받아오면 작성자 = 관리자(1번)으로 초기화
	}

	public Date getCreatedDate(int recipe_id) throws SQLException {
		String sql = "SELECT createdDate " + "FROM users_recipe " + "WHERE recipe_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { recipe_id });

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			Date createdDate = null;
			while (rs.next()) {
				createdDate = rs.getDate("createdDate");
			}
			return createdDate;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	public void updateHits(Recipe recipe) throws SQLException {
		try {
			/* recipe_info에 수정 */
			String sql = "UPDATE recipe_info SET hits = ? "
					+ "WHERE recipe_id = ?";
			Object[] param = new Object[] { recipe.getHits(), recipe.getRecipe_id() };
			jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}

	}

}
