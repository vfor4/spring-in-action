package tacos.dao;

import org.springframework.data.repository.CrudRepository;

import tacos.bean.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{

}
