package com.front.website.repos;

import com.front.website.models.Staff;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StaffRepository extends MongoRepository < Staff, String > {
    
}

