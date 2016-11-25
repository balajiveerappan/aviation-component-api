package com.aviation.poc.service;

import java.util.Date;
import java.util.List;

import com.aviation.poc.entity.Component;

public interface AviationComponentService {

 List<Object> getRemovedComponents(final Date startDate, final Date endDate, final String componentType);
 
 public List<Component> getComponent(Date fromDate, Date toDate);
 
 public com.aviation.poc.vo.ComponentReport getComponents(List<Long> componentIds);
 
 
 List<Long> getComponentsIdsSplashScreen(final String actualData, final String dataType, final Date startDate, final Date endDate);
 

 
}
