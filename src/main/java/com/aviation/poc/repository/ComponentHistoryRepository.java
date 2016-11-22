/*package com.aviation.poc.repository;

import java.io.Serializable;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.aviation.poc.entity.ComponentHistory;


public interface ComponentHistoryRepository extends MongoRepository<ComponentHistory, Serializable>{

	
	
	@Query("{}")
	public Stream<ComponentHistory> getAllComponents();	
	
	
	
	
	
	@Query("SELECT  history as history FROM ComponentHistory history JOIN history.component as comp where comp.componentID in :componentIdList ORDER BY history.fromDate ASC ")
	public List<ComponentHistory> getComponents(@Param("componentIdList")final List<Long> componentIdList);	
	
	@Query("SELECT  distinct(comp.ataSystemNo) as ATANumber, count(comp.ataSystemNo) as count_val FROM ComponentHistory  history JOIN history.component comp where   history.status= :status and  comp.ataSystemNo <> :ataValAsNull  and  history.fromDate between :fromDate and :toDate GROUP BY comp.ataSystemNo ORDER BY count_val DESC")
    public List<Object> getRemovedComponents(@Param("fromDate")final Date fromDate, @Param("toDate")final Date toDate, @Param("status")final String status, @Param("ataValAsNull")final String ataValAsNull );
       
       
       @Query("SELECT  distinct(comp.mfgPartNo) as MFGNumber, count(comp.mfgPartNo) as count_val FROM ComponentHistory  history JOIN history.component comp where   history.status= :status and  comp.mfgPartNo <> :mfgValAsNull  and  history.fromDate between :fromDate and :toDate GROUP BY comp.mfgPartNo ORDER BY count_val DESC")
       public List<Object> getRemovedComponentsMFG(@Param("fromDate")final Date fromDate, @Param("toDate")final Date toDate, @Param("status")final String status, @Param("mfgValAsNull")final String mfgValAsNull );
       
       @Query("SELECT  distinct(comp.cmpySerialNo) as CPNSerialNumber, count(comp.cmpySerialNo) as count_val FROM ComponentHistory  history JOIN history.component comp where   history.status= :status and  comp.cmpySerialNo <> :cpnSerialValAsNull  and  history.fromDate between :fromDate and :toDate GROUP BY comp.cmpySerialNo ORDER BY count_val DESC ")
       public List<Object> getRemovedComponentsCPNSerial(@Param("fromDate")final Date fromDate, @Param("toDate")final Date toDate, @Param("status")final String status, @Param("cpnSerialValAsNull")final String cpnSerialValAsNull );
       
       @Query("SELECT  distinct(comp.tailNo) as TailNumber, count(comp.tailNo) as count_val FROM ComponentHistory  history JOIN history.component comp where   history.status= :status and  comp.tailNo <> :tailRemovalValAsNull  and  history.fromDate between :fromDate and :toDate GROUP BY comp.tailNo ORDER BY count_val DESC")
       public List<Object> getRemovedComponentstailRemoval(@Param("fromDate")final Date fromDate, @Param("toDate")final Date toDate, @Param("status")final String status, @Param("tailRemovalValAsNull")final String tailRemovalValAsNull );
       
       @Query("SELECT component_history as component_history  FROM ComponentHistory  component_history  where  component_history.status= :status and  component_history.tailNo <> :tailValAsNull  and  component_history.fromDate between :fromDate and :toDate GROUP BY component_history.tailNo ")
       public List<Object> getRemovedComponentsTail(@Param("fromDate")final Date fromDate, @Param("toDate")final Date toDate, @Param("status")final String status, @Param("tailValAsNull")final String tailValAsNull );
      
}
*/