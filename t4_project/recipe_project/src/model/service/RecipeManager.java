package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Recipe;
import model.Ingredient;
import model.Procedure;
import model.dao.RecipeDAO;

public class RecipeManager {
	private static RecipeManager recipeMan = new RecipeManager();
	private RecipeDAO recipeDAO;
//	private RecipeAnalysis recipeAnalysis;

	private RecipeManager() {
		try {
			recipeDAO = new RecipeDAO();
//			recipeAnalysis = new RecipeAnalysis(recipeDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static RecipeManager getInstance() {
		return recipeMan;
	}
	
	public int create(Recipe recipe, int memberId) throws SQLException {
		return recipeDAO.create(recipe, memberId);
	}

	public int update(Recipe recipe) throws SQLException {
		return recipeDAO.update(recipe);
	}	

	public int remove(int recipeId) throws SQLException {
		return recipeDAO.remove(recipeId);
	}

	public Recipe findRecipe(int recipeId)
		throws SQLException, RecipeNotFoundException {
		Recipe recipe = recipeDAO.getRecipe(recipeId);
		
		if (recipe == null) {
			try {
				throw new RecipeNotFoundException(recipeId + "는 존재하지 않는 레시피입니다.");
			} catch (RecipeNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
				String ingredients = recipeDAO.getIngredientsName(recipeId);
				
				if (ingredients == null) ingredients = "없음";
				
				recipe.setIngredientsName(ingredients);
			
		}		
		return recipe;
	}

	public Recipe getTopRecipe(int category_id) throws SQLException, RecipeNotFoundException {
		Recipe recipe = recipeDAO.getTopRecipe(category_id);
		
		if (recipe == null) {
			try {
				throw new RecipeNotFoundException("레시피가 존재하지 않습니다.");
			} catch (RecipeNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String ingredients = recipeDAO.getIngredientsName(recipe.getRecipe_id());
			
			if (ingredients == null) ingredients = "없음";
			
			recipe.setIngredientsName(ingredients);
		} 
		
		return recipe;
	}
	
	/* category_id별 레시피 리스트를 따로 가져오기 위한 메소드 (list.jsp에서 사용)*/
	public List<Recipe> findRecipeList(int category_id) throws SQLException, RecipeNotFoundException {
		List<Recipe> recipeList = recipeDAO.getRecipeList(category_id);
		
		if (recipeList == null) {
			try {
				throw new RecipeNotFoundException("레시피가 존재하지 않습니다.");
			} catch (RecipeNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			for (Recipe recipe : recipeList) {
				String ingredients = recipeDAO.getIngredientsName(recipe.getRecipe_id());
				
				if (ingredients == null) ingredients = "없음";
				
				recipe.setIngredientsName(ingredients);
			}
		}
		
		return recipeList;
	}

	/* search를 위한 메소드 */
	public List<Recipe> searchRecipeList(int categoryId, String keyword) throws SQLException, RecipeNotFoundException {
		List<Recipe> recipeList = recipeDAO.searchRecipeList(categoryId, keyword);
		
		if (recipeList == null) {
			try {
				throw new RecipeNotFoundException("레시피가 존재하지 않습니다.");
			} catch (RecipeNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			for (Recipe recipe : recipeList) {
				String ingredients = recipeDAO.getIngredientsName(recipe.getRecipe_id());
				
				if (ingredients == null) ingredients = "없음";
				
				recipe.setIngredientsName(ingredients);
			}
		}
		
		return recipeList;
	}
	
	public List<Integer> findRecommendRecipe(String[] ingredients) throws SQLException {
		return recipeDAO.getRecommendRecipe(ingredients);
	}
	
	public List<Procedure> findProcedure(int recipeId) throws SQLException {
		return recipeDAO.getProcedures(recipeId);
	}
	
//	public List<Recipe> findRecipeList(int currentPage, int countPerPage)
//		throws SQLException {
//		return recipeDAO.getRecipeList(currentPage, countPerPage);
//	}

	public List<Ingredient> findIngredients(int recipeId) throws SQLException {
		return recipeDAO.getIngredients(recipeId);
	}
	
	public RecipeDAO getRecipeDAO() {
		return this.recipeDAO;
	}
	
	/* 해당 유저가 만든 레시피를 가져오는 함수 */
	public List<Recipe> findUserRecipeList(String email_id) throws SQLException, RecipeNotFoundException {
		List<Recipe> recipeList = recipeDAO.getUserRecipeList(email_id);
		
		if (recipeList == null) {
			try {
				throw new RecipeNotFoundException("레시피가 존재하지 않습니다.");
			} catch (RecipeNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			for (Recipe recipe : recipeList) {
				String ingredients = recipeDAO.getIngredientsName(recipe.getRecipe_id());
				
				if (ingredients == null) ingredients = "없음";
				
				recipe.setIngredientsName(ingredients);
			}
		}
		
		return recipeList;
	}
	
	public void updateHits(Recipe recipe) throws SQLException {
		recipeDAO.updateHits(recipe);
	}
	
}
