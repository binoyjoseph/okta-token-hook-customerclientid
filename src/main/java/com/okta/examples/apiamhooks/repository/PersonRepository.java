package com.okta.examples.apiamhooks.repository;


import com.okta.examples.apiamhooks.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByEmail(String email);
}
