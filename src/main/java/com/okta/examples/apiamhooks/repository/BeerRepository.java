package com.okta.examples.apiamhooks.repository;

import com.okta.examples.apiamhooks.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Long> {

    Beer findByName(String name);
}