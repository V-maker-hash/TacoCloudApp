package com.example.tacocloud.jpaRepositories;

import com.example.tacocloud.domain.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
