package com.example.tacocloud.jdbcrepositories;

import com.example.tacocloud.domain.Taco;

public interface TacoRepository {
    Taco save(Taco design);
}
