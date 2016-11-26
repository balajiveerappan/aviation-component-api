package com.aviation.poc.vo;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentHistoryVO {
	
	private Date  fromDate;
	private Date  todate;
	private String tailNo;
	private String  status;
	private String maint_stn;
	private String dept;
	private String status_reason;
	private String discrepency_no;
	private String positionComponentRemoval;
			
}
