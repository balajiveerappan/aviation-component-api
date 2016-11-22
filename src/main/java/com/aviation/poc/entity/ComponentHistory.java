package com.aviation.poc.entity;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="componentHistoryDetails")
public class ComponentHistory {
	
	@Id
	private Long historyID;
	
	@DBRef
	private Component component;
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private Date  fromDate;
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private Date  todate;
	
	private String tailNo;
	private String  status;
	private String maint_stn;
	
	private String dept;
	
	private String status_reason;
	
	private String discrepency_no;
	
	private String positionComponentRemoval;

}
