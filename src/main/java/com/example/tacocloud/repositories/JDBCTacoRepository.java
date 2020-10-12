package com.example.tacocloud.repositories;

import com.example.tacocloud.domain.Taco;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCTacoRepository implements TacoRepository {
    @Override
    public Taco save(Taco design) {
        return null;
    }
}
