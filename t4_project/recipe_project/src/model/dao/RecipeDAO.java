package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Recipe;

public class RecipeDAO {
private JDBCUtil jdbcUtil = null;
	
	public RecipeDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	// 레시피 추가
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

	// 레시피 수정
	public int update(Recipe recipe) throws SQLException {
		String sql = "UPDATE recipe_info "
					+ "SET category_id=?, rname=?, time=?, result_img=?, hits=? "
					+ "WHERE recipe_id=?";
		Object[] param = new Object[] {recipe.getCategory_id(), 
				recipe.getRname(), recipe.getTime(), recipe.getResult_img(), recipe.getHits(), recipe.getRecipe_id()};				
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

	// 레시피 삭제
	public int remove(int recipeId) throws SQLException {
		String sql = "DELETE FROM recipe_info WHERE recipe_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipeId});	// JDBCUtil에 delete문과 매개 변수 설정

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

	
	// 주어진 recipe_id에 해당하는 레시피 정보를 데이터베이스에서 찾아서 Recipe 도메인 클래스에 저장하여 반환.
	public Recipe recipeView(int recipeId) throws SQLException {
        String sql = "SELECT category_id, rname, time, result_img, hits "
        			+ "FROM recipe_info "
        			+ "WHERE recipe_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipeId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				Recipe recipe = new Recipe (		// User 객체를 생성하여 학생 정보를 저장
					recipeId,
					rs.getInt("category_id"),
					rs.getString("rname"),
					rs.getString("time"),
					rs.getString("result_img"),
					rs.getInt("hits"));
				return recipe;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	// 주어진 category_id에 따라 레시피들의 정보를 List<Recipe>의 형태로 출력
	public List<Recipe> findRecipeList() throws SQLException {
        String sql = "SELECT result_img, rname, (요리에 필요한 재료), time, hits " 
        		   + "FROM recipe_info "	
        		   + "ORDER BY hits DESC";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Recipe> recipeList = new ArrayList<Recipe>();	// Recipe들의 리스트 생성
			while (rs.next()) {
				Recipe recipe = new Recipe (		// Recipe 객체를 생성하여 recipe 정보를 저장
					rs.getInt("recipe_id"),
					rs.getInt("category_id"),
					rs.getString("rname"),
					rs.getString("time"),
					rs.getString("result_img"),
					rs.getInt("hits"));	
				recipeList.add(recipe);				// List에  Recipe 객체 저장
			}		
			return recipeList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	

}