package com.aviation.poc.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordData {

private String componentId;
private Date fromDate;
private String status;
private Date toDate;

private String maintStn;
private String statusReason;
private String dept;
private String discerpancyNo;
private String positioComponentRemoval;

private String tailNo;
private String ataSystemNumber;

private String companyPartNumber;
private String fleetNo;
private String mfgPartNumber;

private String companySerialNumber;
private Date statusUpdatedDate;

private String subFleetNumber;
private String mfgSerialNumber;
private String classification;
private String description;
private String master;
private String cycles;

private String timeSinceInstall;


}
