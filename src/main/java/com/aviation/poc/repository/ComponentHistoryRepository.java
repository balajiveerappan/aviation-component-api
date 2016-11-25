package com.aviation.poc.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aviation.poc.entity.ComponentHistory;


public interface ComponentHistoryRepository extends CrudRepository<ComponentHistory, Serializable>{

	
	@Query("SELECT  history as history FROM ComponentHistory history JOIN history.component as comp where comp.componentID in :componentIdList ORDER BY history.fromDate ASC ")
	//@Query("SELECT  history as history FROM ComponentHistory history JOIN history.component as comp where comp.componentID =:componentIdList")
	public List<ComponentHistory> getComponents(@Param("componentIdList")final List<Long> componentIdList);	
	
	
	
	//@Query("SELECT  history.component  FROM ComponentHistory history where history.component.componentID in :componentIdList")
	//@Query("SELECT  comp as comp FROM Component comp where comp.componentID in :componentIdList")
	//@Query("SELECT  comp as comp FROM Component comp where comp.statusUpdatedDate between :fromDate and :toDate")
	
	//and  history.fromDate between :fromDate and :toDate"
	//public List<ComponentHistory> getRemovedComponents(@Param("fromDate")final Date fromDate, @Param("toDate")final Date toDate, @Param("status")final String status );
	
	//@Query("SELECT  distinct(comp.ataSystemNo) as history FROM ComponentHistory  history JOIN history.component comp where history.status= :status and  history.fromDate between :fromDate and :toDate")
	
	//select distinct(comp.ata_system_no), count(*) from component_history history join component comp  on history.componentid=comp.componentid and  history.status='Removed' and history.from_date between "2014-10-08" and "2016-10-08" group by comp.ata_system_no;
	
	   @Query("SELECT  distinct(comp.ataSystemNo) as ATANumber, count(comp.ataSystemNo) as count_val FROM ComponentHistory  history JOIN history.component comp where   history.status= :status and  comp.ataSystemNo <> :ataValAsNull  and  history.fromDate between :fromDate and :toDate GROUP BY comp.ataSystemNo ORDER BY count_val DESC")
       public List<Object> getRemovedComponents(@Param("fromDate")final Date fromDate, @Param("toDate")final Date toDate, @Param("status")final String status, @Param("ataValAsNull")final String ataValAsNull );
       
       
       
       @Query("SELECT  distinct(comp.companyPartNo) as MFGNumber, count(comp.companyPartNo) as count_val FROM ComponentHistory  history JOIN history.component comp where   history.status= :status and  comp.companyPartNo <> :mfgValAsNull  and  history.fromDate between :fromDate and :toDate GROUP BY comp.companyPartNo ORDER BY count_val DESC")
       public List<Object> getRemovedComponentsCPN(@Param("fromDate")final Date fromDate, @Param("toDate")final Date toDate, @Param("status")final String status, @Param("mfgValAsNull")final String mfgValAsNull );
       
       @Query("SELECT  distinct(comp.cmpySerialNo) as CPNSerialNumber, count(comp.cmpySerialNo) as count_val FROM ComponentHistory  history JOIN history.component comp where   history.status= :status and  comp.cmpySerialNo <> :cpnSerialValAsNull  and  history.fromDate between :fromDate and :toDate GROUP BY comp.cmpySerialNo ORDER BY count_val DESC ")
       public List<Object> getRemovedComponentsCPNSerial(@Param("fromDate")final Date fromDate, @Param("toDate")final Date toDate, @Param("status")final String status, @Param("cpnSerialValAsNull")final String cpnSerialValAsNull );
       
       @Query("SELECT  distinct(comp.tailNo) as TailNumber, count(comp.tailNo) as count_val FROM ComponentHistory  history JOIN history.component comp where   history.status= :status and  comp.tailNo <> :tailRemovalValAsNull  and  history.fromDate between :fromDate and :toDate GROUP BY comp.tailNo ORDER BY count_val DESC")
       public List<Object> getRemovedComponentstailRemoval(@Param("fromDate")final Date fromDate, @Param("toDate")final Date toDate, @Param("status")final String status, @Param("tailRemovalValAsNull")final String tailRemovalValAsNull );
       
       @Query("SELECT component_history as component_history  FROM ComponentHistory  component_history  where  component_history.status= :status and  component_history.tailNo <> :tailValAsNull  and  component_history.fromDate between :fromDate and :toDate GROUP BY component_history.tailNo ")
       public List<Object> getRemovedComponentsTail(@Param("fromDate")final Date fromDate, @Param("toDate")final Date toDate, @Param("status")final String status, @Param("tailValAsNull")final String tailValAsNull );
      
       
       @Query("SELECT  comp.componentID as com FROM ComponentHistory  history JOIN history.component comp where comp.ataSystemNo= :actualData and history.status= :status and history.fromDate between :startDate and :endDate ")
       public List<Long> getComponentsIdsATA(@Param("actualData")String actualData,@Param("status")String status,@Param("startDate")Date startDate, @Param("endDate")final Date endDate);
       
       @Query("SELECT  comp.componentID as com FROM ComponentHistory  history JOIN history.component comp where comp.companyPartNo= :actualData and history.status= :status and history.fromDate between :startDate and :endDate ")
       public List<Long> getComponentsIdsCPN(@Param("actualData")String actualData,@Param("status")String status,@Param("startDate")Date startDate, @Param("endDate")final Date endDate);
       
       @Query("SELECT  comp.componentID as com FROM ComponentHistory  history JOIN history.component comp where comp.cmpySerialNo = :actualData and history.status= :status and history.fromDate between :startDate and :endDate ")
       public List<Long> getComponentsIdsCSN(@Param("actualData")String actualData,@Param("status")String status,@Param("startDate")Date startDate, @Param("endDate")final Date endDate);

       @Query("SELECT  comp.componentID as com FROM ComponentHistory  history JOIN history.component comp where comp.tailNo= :actualData and history.status= :status and history.fromDate between :startDate and :endDate ")
       public List<Long> getComponentsIdsTail(@Param("actualData")String actualData,@Param("status")String status,@Param("startDate")Date startDate, @Param("endDate")final Date endDate);
     

       
}
