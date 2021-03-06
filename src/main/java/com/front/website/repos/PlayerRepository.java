package com.front.website.repos;

import com.front.website.models.Player;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository < Player, String > {
    
}
