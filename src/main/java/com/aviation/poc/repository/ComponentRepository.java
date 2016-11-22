package com.aviation.poc.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aviation.poc.entity.Component;



public interface ComponentRepository extends MongoRepository<Component, Serializable>{

	/*@Query("SELECT comp as comp FROM Component comp where comp.statusUpdatedDate between :fromDate and :toDate")
	public List<Component> getComponent(@Param("fromDate")final Date fromDate, @Param("toDate")final Date toDate);
*/	
}
