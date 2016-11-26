package com.aviation.poc.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComponentHistory {

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date installationDate;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date removalDate;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date repairDate;
	private String maintStnRemoval;
	private String maintStnInstallation;;
	private String maintStnRepair;
	private String installationDept;
	private String removalDept;
	private String repairDept;
	private String statusReasonRemoval;
	private String discrepencyNoRemoval;
	private String positionComponent;

}
