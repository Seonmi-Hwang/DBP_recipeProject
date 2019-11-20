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
	
	public int create(Recipe recipe) throws SQLException {
		return recipeDAO.create(recipe);
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
		}		
		return recipe;
	}

	public List<Recipe> findRecipeList(int categoryId) throws SQLException, RecipeNotFoundException {
		List<Recipe> recipeList = recipeDAO.getRecipeList(categoryId);
		
		for (Recipe recipe : recipeList) {
			String ingredients = recipeDAO.getIngredients(recipe.getRecipe_id());
			
			if (ingredients == null) ingredients = "없음";
			
			recipe.setIngredientsName(ingredients);
		}
		
		if (recipeList == null) {
			try {
				throw new RecipeNotFoundException("레시피가 존재하지 않습니다.");
			} catch (RecipeNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
		return recipeList;
	}
	
	public List<Recipe> findUserRecipeList(String email_id) throws SQLException, RecipeNotFoundException {
		List<Recipe> recipeList = recipeDAO.getUserRecipeList(email_id);
		
		for (Recipe recipe : recipeList) {
			String ingredients = recipeDAO.getIngredients(recipe.getRecipe_id());
			
			if (ingredients == null) ingredients = "없음";
			
			recipe.setIngredientsName(ingredients);
		}
		
		if (recipeList == null) {
			try {
				throw new RecipeNotFoundException("레시피가 존재하지 않습니다.");
			} catch (RecipeNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
		return recipeList;
	}

	
	public List<Recipe> searchRecipeList(int categoryId, String keyword) throws SQLException, RecipeNotFoundException {
		List<Recipe> recipeList = recipeDAO.searchRecipeList(categoryId, keyword);
		
		for (Recipe recipe : recipeList) {
			String ingredients = recipeDAO.getIngredients(recipe.getRecipe_id());
			recipe.setIngredientsName(ingredients);
		}
		
		if (recipeList == null) {
			try {
				throw new RecipeNotFoundException("레시피가 존재하지 않습니다.");
			} catch (RecipeNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
		return recipeList;
	}
	
	public List<Integer> findRecommendRecipe(List<Integer> ingredients) throws SQLException {
		return recipeDAO.getRecommendRecipe(ingredients);
	}
	
	public List<Procedure> findProcedure(int recipeId) throws SQLException {
		return recipeDAO.getProcedures(recipeId);
	}
	
//	public List<Recipe> findRecipeList(int currentPage, int countPerPage)
//		throws SQLException {
//		return recipeDAO.getRecipeList(currentPage, countPerPage);
//	}
	
	/* YJ */
	public List<Ingredient> findIngredients(int recipeId) throws SQLException {
		return recipeDAO.getIngredientsName(recipeId);
	}
	
	public RecipeDAO getRecipeDAO() {
		return this.recipeDAO;
	}
	
	
}
