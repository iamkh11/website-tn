package com.front.website.repos;

import com.front.website.models.News;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsRepository extends MongoRepository < News, String > {
    
}

