package com.example.repositories;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JDBCIngredientRepository implements IngredientRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query("select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }


    @Override
    public Ingredient findOne(String id) {
        return jdbcTemplate.queryForObject("select id, name, type from Ingredient where id=?",
                this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return null;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int i) throws SQLException {
        return new Ingredient(resultSet.getString("id"),
                resultSet.getString("name"),
                Type.valueOf(resultSet.getString("type")));
    }
}
