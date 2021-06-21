package tacos.service;

import tacos.bean.Ingredient;

public interface IngredientService {

	public Iterable<Ingredient> findAll();
}
