package com.aviation.poc.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aviation.poc.entity.Component;



public interface ComponentRepository extends CrudRepository<Component, Serializable>{

	@Query("SELECT comp as comp FROM Component comp where comp.statusUpdatedDate between :fromDate and :toDate")
	public List<Component> getComponent(@Param("fromDate")final Date fromDate, @Param("toDate")final Date toDate);
	
}
