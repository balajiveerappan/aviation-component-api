package com.aviation.poc.service;

import java.util.Date;
import java.util.List;

public interface AviationComponentService {

 List<Object> getRemovedComponents(final Date startDate, final Date endDate, final String componentType);
}
