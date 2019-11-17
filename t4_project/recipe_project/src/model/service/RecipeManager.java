package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Recipe;
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
	
	public int create(Recipe recipe) throws SQLException, ExistingMemberException {
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

	public List<Recipe> findRecipeList() throws SQLException {
		return recipeDAO.getRecipeList();
	}
	
	public List<Integer> findRecommendRecipe(List<Integer> ingredients) {
		return recipeDAO.getRecommendRecipe(ingredients);
	}
	
	public List<Procedure> findProcedure(int recipeId) {
		return recipeDAO.getProcedures(recipeId);
	}
	
//	public List<Recipe> findRecipeList(int currentPage, int countPerPage)
//		throws SQLException {
//		return recipeDAO.getRecipeList(currentPage, countPerPage);
//	}
	
	public RecipeDAO getRecipeDAO() {
		return this.recipeDAO;
	}
	
	public List<String> findIngredientsName(int recipeId) {
		return recipeDAO.getIngredientsName(recipeId);
	}

}
