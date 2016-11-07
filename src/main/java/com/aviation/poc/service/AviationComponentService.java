package com.aviation.poc.service;

import java.util.Date;
import java.util.List;

public interface AviationComponentService {

	List<Object> getRemovedComponents(Date startDate, Date endDate);
	List<Object> getRemovedComponentsMFG(Date startDate, Date endDate);
	List<Object> getRemovedComponentsCPNSerial(Date startDate, Date endDate);
	List<Object> getRemovedComponentsTailNoOfRemoval(Date startDate, Date endDate);
}
