package com.aviation.poc.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.aviation.poc.entity.Component;

public interface ComponentRepository extends MongoRepository<Component, Serializable>{

		@Query("{master : ?0}")
	    public Component findByMasterId(String master);
}
