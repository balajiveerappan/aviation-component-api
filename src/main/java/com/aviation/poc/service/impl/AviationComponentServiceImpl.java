package com.aviation.poc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aviation.poc.entity.Component;
import com.aviation.poc.repository.ComponentHistoryRepository;
import com.aviation.poc.repository.ComponentRepository;
import com.aviation.poc.service.AviationComponentService;


@Service
public class AviationComponentServiceImpl  implements AviationComponentService{

	@Autowired
	private ComponentHistoryRepository historyRepo;
	
	@Autowired
	private ComponentRepository componentRepository;
	

	@Override
	public List<Object> getRemovedComponents(final Date startDate, final Date endDate, final String componentType) {

		switch(componentType) {
		
		case "ATA" : return historyRepo.getRemovedComponents(startDate, endDate, "Removed", "null")
					.stream().limit(10).collect(Collectors.toList());
		
		case "MFG" : return historyRepo.getRemovedComponentsMFG(startDate, endDate, "Removed", "null")
						.stream().limit(10).collect(Collectors.toList());
		
		case "CPN" : return historyRepo.getRemovedComponentsCPNSerial(startDate, endDate, "Removed", "null")
							.stream().limit(10).collect(Collectors.toList());
						
		case "TAIL" : return historyRepo.getRemovedComponentstailRemoval(startDate, endDate, "Removed", "null")
				.stream().limit(10).collect(Collectors.toList());
		
		}
		
		return null;
	}
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Component> getComponent(Date fromDate, Date toDate) {

		final List<Component> component = componentRepository.getComponent(fromDate, toDate);
		return component;
	}
}
