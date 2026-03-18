package com.example.movie_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movie_service.entity.Keyword;
import com.example.movie_service.repository.KeywordRepository;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.transaction.Transactional;

@Service
public class KeywordService {
    
    @Autowired
    private KeywordRepository keywordRepository;

    @Transactional
    public List<Keyword> saveKeywords(JsonNode items){
        List<Keyword> keywords = new ArrayList<>();
        for (JsonNode item : items) {

            String keyword_name = item.get("name").toString().replace("\"", "");
            Keyword keyword = keywordRepository.findByName(keyword_name)
                .orElseGet(() -> {
                    Keyword newKeyword = new Keyword();
                    newKeyword.setName(keyword_name);
                    return keywordRepository.save(newKeyword);
                });

            keywords.add(keyword);
        }
        return keywords;
    }
}
