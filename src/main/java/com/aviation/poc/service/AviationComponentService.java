package com.aviation.poc.service;

import java.util.Date;
import java.util.List;

public interface AviationComponentService {

	List<Object> getRemovedComponents(final Date startDate, final Date endDate);
	List<Object> getRemovedComponentsMFG(final Date startDate, final Date endDate);
	List<Object> getRemovedComponentsCPNSerial(final Date startDate, final Date endDate);
	List<Object> getRemovedComponentsTailNoOfRemoval(final Date startDate, final Date endDate);
}
