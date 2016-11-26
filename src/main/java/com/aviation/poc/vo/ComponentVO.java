package com.aviation.poc.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComponentVO {

	private String componentID;
	private String description;
	private String cmpySerialNo;
	private String mnfgSerialNo;
	private String classification;
	private String fleetNo;
	private String subfleetNo;
	private String ataSystemNo;
	private String tailNo;
	private String companyPartNo;
	private String mfgPartNo;
	private String master;
	private Date statusUpdatedDate;
	private String status;

}
