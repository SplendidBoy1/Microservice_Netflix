package com.example.movie_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.movie_service.entity.Country;

import com.example.movie_service.repository.CountryRepository;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.transaction.Transactional;


@Service
public class CountryService {
    

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public List<Country> saveCategories(JsonNode items){
        List<Country> countries = new ArrayList<>();
        for (JsonNode item : items) {

            String cat_name = item.get("name").toString().replace("\"", "");
            String cat_slug = item.get("slug").toString().replace("\"", "");
            Country country = countryRepository
                .findByName(cat_name)
                .orElseGet(() -> {
                    Country newCoun = new Country();
                    newCoun.setName(cat_name);
                    newCoun.setSlug(cat_slug);
                    return countryRepository.save(newCoun);
                });

        countries.add(country);
        }
        return countries;
    }

    public void deleteAll(){
        countryRepository.deleteAllInBatch();
    }

}
