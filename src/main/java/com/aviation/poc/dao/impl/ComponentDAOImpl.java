package com.aviation.poc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.aviation.poc.dao.ComponentDAO;
import com.aviation.poc.entity.Component;
import com.aviation.poc.vo.ComponentHistoryVO;

public class ComponentDAOImpl implements ComponentDAO{

	@Autowired
	private MongoOperations mongoOps;
	
	@Override
	public List<ComponentHistoryVO> getComponentHistoryVO(final List<String> masterIdList) {
		
		final List<ComponentHistoryVO> historyVOList = new ArrayList<>();
		mongoOps.find(Query.query(Criteria.where("masterId")
				.in(masterIdList)), Component.class, "components").stream()
		.map(component -> component.getHistory()).flatMap(componentHistory -> componentHistory.stream())
			.filter(history -> (history.getInstallationDate() != null)).forEach(history -> {
				// Installation Date
				final ComponentHistoryVO componentHistoryVOInstalled = new ComponentHistoryVO();
				componentHistoryVOInstalled.setDept(history.getInstallationDept());
				componentHistoryVOInstalled.setFromDate(history.getInstallationDate());
				componentHistoryVOInstalled.setTodate(history.getRemovalDate());
				componentHistoryVOInstalled.setMaint_stn(history.getMaintStnInstallation());
				historyVOList.add(componentHistoryVOInstalled);
				// Removal Date
				final ComponentHistoryVO componentHistoryVORemoval = new ComponentHistoryVO();
				componentHistoryVORemoval.setDept(history.getRemovalDept());
				componentHistoryVORemoval.setFromDate(history.getRemovalDate());
				componentHistoryVORemoval.setMaint_stn(history.getMaintStnRemoval());
				historyVOList.add(componentHistoryVORemoval);
				// Repair Data 
				final ComponentHistoryVO componentHistoryVORepair = new ComponentHistoryVO();
				componentHistoryVORepair.setDept(history.getRepairDept());
				componentHistoryVORepair.setFromDate(history.getRepairDate());
				componentHistoryVORepair.setMaint_stn(history.getMaintStnRepair());
				historyVOList.add(componentHistoryVORepair);
				

			});
		
		return null;
	}

	
}
