package com.example.tacocloud.jpaRepositories;

import com.example.tacocloud.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
