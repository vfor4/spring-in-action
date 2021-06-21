package tacos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.bean.Ingredient;
import tacos.dao.IngredientRepository;
import tacos.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;

	@Override
	public Iterable<Ingredient> findAll() {
		return ingredientRepository.findAll();
	}
	
	
}
