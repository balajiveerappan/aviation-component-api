package com.aviation.poc.vo;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class MasterData {

@Id
private String Id;

private String H_RCN;
private String HR_MPN;
private String H_SERIAL ;
private String HR_MSN	;
private String NOUN;
private String DESCRIPTION;
private String H_ACN;
private String FLEET;
private String FLEET_MODEL_CD;
private String H_POS;

@DateTimeFormat(iso=ISO.DATE_TIME)
private Date HI_DTE;
private String HI_STA;
private String HI_DEPT;

@DateTimeFormat(iso=ISO.DATE_TIME)
private Date HR_DTE;

private String HR_STA;
private String HR_DEPT;
private String HR_REASON;
private String HR_TSI;
private String HR_CSI;
private String HR_ATA;
private String HR_D_NBR;
private String HS_STA;
private String HS_DEPT;

@DateTimeFormat(iso=ISO.DATE_TIME)
private Date HS_REPAIR_DTE;
private String HS_REPAIR_TYPE;
private String HS_REPAIR_ODR_NBR;
private String master;



}
