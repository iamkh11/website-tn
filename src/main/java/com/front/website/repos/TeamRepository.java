package com.front.website.repos;

import com.front.website.models.Match;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository < Match, String > {
    
}
