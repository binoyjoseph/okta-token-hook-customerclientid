package com.okta.examples.apiamhooks.controller;

import com.okta.examples.apiamhooks.model.Beer;
import com.okta.examples.apiamhooks.model.Person;
import com.okta.examples.apiamhooks.repository.BeerRepository;
import com.okta.examples.apiamhooks.repository.PersonRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class APIController {

    BeerRepository beerRepository;
    PersonRepository personRepository;

    public APIController(BeerRepository beerRepository, PersonRepository personRepository) {
        this.beerRepository = beerRepository;
        this.personRepository = personRepository;
    }

    @GetMapping("/good-beers")
    public Collection<Beer> goodBeers() {

        return beerRepository.findAll().stream()
            .filter(this::isGreat)
            .collect(Collectors.toList());
    }

    @GetMapping("/beers")
    public Collection<Beer> beers() {
        return beerRepository.findAll();
    }

    private boolean isGreat(Beer beer) {
        return !beer.getName().equals("Budweiser") &&
            !beer.getName().equals("Coors Light") &&
            !beer.getName().equals("PBR");
    }

    @PostMapping("/add-beer")
    @ResponseBody List<Beer> addBeer(@RequestBody Beer beer, @AuthenticationPrincipal Principal principal) {

        beer = findOrCreateBeer(beer);
        Person p = findOrCreatePerson(principal);

        // no need to add if it's already there
        if (!p.getFavoriteBeers().contains(beer)) {
            p.getFavoriteBeers().add(beer);
            personRepository.save(p);
        }

        return p.getFavoriteBeers();
    }

    @GetMapping("/me-beers")
    @ResponseBody List<Beer> listBeers(@AuthenticationPrincipal Principal principal) {
        return findOrCreatePerson(principal).getFavoriteBeers();
    }

    private Person findOrCreatePerson(Principal principal) {
        Person p = personRepository.findByEmail(principal.getName());
        if (p == null) {
            p = new Person();
            p.setEmail(principal.getName());
            personRepository.save(p);
        }
        return p;
    }

    private Beer findOrCreateBeer(Beer beerToFind) {
        Beer beer = beerRepository.findByName(beerToFind.getName());
        if (beer == null) {
            beer = new Beer();
            beer.setName(beerToFind.getName());
            beerRepository.save(beer);
        }
        return beer;
    }
}
