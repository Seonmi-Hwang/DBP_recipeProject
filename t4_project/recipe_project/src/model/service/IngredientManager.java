package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Ingredient;
import model.dao.IngredientDAO;

public class IngredientManager {
	private static IngredientManager IngreMan = new IngredientManager();
	private IngredientDAO IngreDAO;

	private IngredientManager() {
		try {
			IngreDAO = new IngredientDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static IngredientManager getInstance() {
		return IngreMan;
	}
	
	public int create(Ingredient ingredient) throws SQLException {
		return IngreDAO.create(ingredient);
	}

	public Ingredient findIngredient(int ingreId)
			throws SQLException, IngredientNotFoundException {
			Ingredient ingre = IngreDAO.findIngredientname(ingreId);
			
			if (ingre == null) {
				throw new IngredientNotFoundException(ingreId + "는 존재하지 않는 아이디입니다.");
			}		
			return ingre;
		}
	
	public List<Ingredient> findIngredientList(String category) throws SQLException {
		return IngreDAO.findCategoryingredientList(category);
	}
	
	
	public List<Ingredient> findingredientname() throws SQLException {
		List<Ingredient> ingre = IngreDAO.findAllingredientList(); 

		return ingre;
	}
	
	//안쓰는것같아서 없애버림 by MH
//	public IngredientDAO getIngredientDAO() {
//		return this.IngreDAO;
//	}
	
	public int findIdByName(String iname) throws SQLException {
		return IngreDAO.findIdByName(iname);
	}
}
