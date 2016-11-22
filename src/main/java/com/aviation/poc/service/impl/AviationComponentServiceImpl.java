package com.aviation.poc.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aviation.poc.entity.Component;
import com.aviation.poc.service.AviationComponentService;
import com.aviation.poc.vo.ComponentHistoryGroupVO;


@Service
public class AviationComponentServiceImpl  implements AviationComponentService{

	/*@Autowired
	private ComponentHistoryRepository historyRepo;
	
	@Autowired
	private ComponentRepository componentRepository;*/
	

	@Override
	public List<Object> getRemovedComponents(final Date startDate, final Date endDate, final String componentType) {

		switch(componentType) {
		
		/*case "ATA" : return historyRepo.getRemovedComponents(startDate, endDate, "Removed", "null")
					.stream().limit(10).collect(Collectors.toList());
		
		case "MFG" : return historyRepo.getRemovedComponentsMFG(startDate, endDate, "Removed", "null")
						.stream().limit(10).collect(Collectors.toList());
		
		case "CPN" : return historyRepo.getRemovedComponentsCPNSerial(startDate, endDate, "Removed", "null")
							.stream().limit(10).collect(Collectors.toList());
						
		case "TAIL" : return historyRepo.getRemovedComponentstailRemoval(startDate, endDate, "Removed", "null")
				.stream().limit(10).collect(Collectors.toList());
		*/
		}
		
		return null;
	}
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Component> getComponent(Date fromDate, Date toDate) {

		final List<Component> component = null;//componentRepository.getComponent(fromDate, toDate);
		return component;
	}
	
	public com.aviation.poc.vo.ComponentReport getComponents(List<Long> componentIds) {
		
		 List<com.aviation.poc.entity.ComponentHistory> componentHisList = null;// historyRepo.getComponents(componentIds);
		
		 List<com.aviation.poc.vo.HisotryComponenItemVO> itemList = new ArrayList<com.aviation.poc.vo.HisotryComponenItemVO>();
		 Map<String, List<String>> serialNumberMap = new HashMap<String, List<String>> ();
		 
		 com.aviation.poc.vo.ComponentReport componentList = new com.aviation.poc.vo.ComponentReport();
		 Set<com.aviation.poc.vo.ComponentHistoryGroupVO> groupSet = new HashSet<com.aviation.poc.vo.ComponentHistoryGroupVO>();
		 int count=0;
		 SimpleDateFormat  outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
		 String startDate = null;
		 String endDate = null;
		 String popup = null;
		 for(com.aviation.poc.entity.ComponentHistory componentHistory : componentHisList){
			 com.aviation.poc.vo.ComponentHistoryGroupVO group = new com.aviation.poc.vo.ComponentHistoryGroupVO();
			 com.aviation.poc.vo.HisotryComponenItemVO item = new com.aviation.poc.vo.HisotryComponenItemVO();
			 group.setId(componentHistory.getComponent().getComponentID().toString());
			 group.setContent(componentHistory.getComponent().getCmpySerialNo());
//			 group.setTitle("I will show details");
			 groupSet.add(group);
			
			 
			 
			 item.setId(String.valueOf(count++));
			 item.setContent("");
			 startDate = outputFormatter.format(componentHistory.getFromDate());
			 if(componentHistory.getTodate()!=null){
				 endDate = outputFormatter.format(componentHistory.getTodate()) ;
			 }else{
				 endDate = outputFormatter.format(new Date());
			 }
			 if(componentHistory.getStatus().toString().equalsIgnoreCase("Removed")){
				 item.setClassName("negative");
				// item.setType("background");
				 item.setType("range");
				 popup = "Classification : "+componentHistory.getComponent().getClassification().toString();
				 popup = popup +"<br/>Description : "+ componentHistory.getComponent().getDescription().toString();
				 popup = popup +"<br/>Tail No : "+ componentHistory.getComponent().getTailNo().toString();
//				 popup = popup +"<br/>HI_DTE : "+ componentHistory.getComponent().getDescription().toString();
//				 popup = popup +"<br/>HI_STA : "+ componentHistory.getComponent().getDescription().toString();
//				 popup = popup +"<br/>HI_DEPT : "+ componentHistory.getComponent().getDescription().toString();
				 popup = popup +"<br/>Removal Date : "+ componentHistory.getFromDate().toString();
				 popup = popup +"<br/>Removal Station : "+ componentHistory.getMaint_stn().toString();
				 popup = popup +"<br/>Removal Department : "+ componentHistory.getDept().toString();
				 String removalreason = null;
				 if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("01")){
					 removalreason = "SCHEDULED REMOVAL / INSTALLATION";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("02")){
					 removalreason = "PREMATURE REMOVAL / INSTALLATION";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("03")){
					 removalreason = "PART REMOVED FOR CONVENIENCE";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("04")){
					 removalreason = "CANNED PART TO INST ON ANOTHER A/C";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("05")){
					 removalreason = "INSTALLATION ONLY   ** NO PART REMOVED **";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("06")){
					 removalreason = "REMOVAL ONLY    ** NO PART INSTALLED **";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("07")){
					 removalreason = "SWAP COMPONENT POSITIONS";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("08")){
					 removalreason = "REPLACEMENT OF CANNIBALIZED PART";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("09")){
					 removalreason = "EO, AD, FCD, INSP \"P\" PREFACED CPNS ONLY";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("10")){
					 removalreason = "SUB-ASSY REMOVED WITH ASSY";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("12")){
					 removalreason = "FOUND AT LINE / HANGAR";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("13")){
					 removalreason = "FAILED OPS CK & REPLACED WITH SERVICEABLE";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("14")){
					 removalreason = "FAILED OPS CK & ORIGINAL UNIT REINSTALLED";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("17")){
					 removalreason = "MISCELLANEOUS";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("18")){
					 removalreason = "SUB COMP REMOVED FROM AN UNINST MAJOR ASSY";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("23")){
					 removalreason = "INSTALL BORROWED PART AND REMOVED FEDEX PART";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("24")){
					 removalreason = "REMOVE BORROWED PART AND INSTALL FEDEX PART";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("25")){
					 removalreason = "BORROWED PART   ** INSTALLATION ONLY **";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("26")){
					 removalreason = "BORROWED PART   ** REMOVAL ONLY **";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("27")){
					 removalreason = "INSTALL ONLY FOR LOG CORRECTION";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("28")){
					 removalreason = "REMOVAL ONLY FOR LOG CORRECTION";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("29")){
					 removalreason = "CANNIBALIZED SUB-ASSY FOR ANOTHER A/C OR ASSY";
				 }else if(componentHistory.getStatus_reason().toString().equalsIgnoreCase("70")){
					 removalreason = "DAMAGED DURING INSTALLATION";
				 }else{
					 removalreason = "NO REASON";
				 }
				 popup = popup +"<br/>Removal Reason : "+ removalreason;
//				 popup = popup +"<br/>HS_STA : "+ componentHistory.getComponent().getDescription().toString();
//				 popup = popup +"<br/>HS_DEPT : "+ componentHistory.getComponent().getDescription().toString();
//				 popup = popup +"<br/>HS_REPAIR_TYPE : "+ componentHistory.getComponent().getDescription().toString();
//				 popup = popup +"<br/>HS_REPAIR_ODR_NBR : "+ componentHistory.getComponent().getDescription().toString();
				 
				 item.setContent("<div style=\"height: 15px;\"><img class='triangleImage' title=\""+popup+"\" src=\"img/triangle.png\" style=\"width: 15px; height: 15px;\"></div>");
//				 item.setContent("<div style=\"height: 15px;\"><img title=\"<span style='color:blue'>That's what this widget is<br/> test</span>\" src=\"img/triangle.png\" style=\"width: 15px; height: 15px;\"></div>");
//				 item.setTitle("this is test title");
			 }else if(componentHistory.getStatus().toString().equalsIgnoreCase("Installed Unit")){
				 item.setClassName("positive");
				 //item.setType("background");
				 popup = "Classification : "+componentHistory.getComponent().getClassification().toString();
				 popup = popup +"<br/>Description : "+ componentHistory.getComponent().getDescription().toString();
				 popup = popup +"<br/>Sub Fleet No : "+ componentHistory.getComponent().getSubfleetNo().toString();
				 if(componentHistory.getComponent().getMfgPartNo() != null){
				 popup = popup +"<br/>Manufacturing Part No : "+ componentHistory.getComponent().getMfgPartNo().toString();
				 }
				 popup = popup +"<br/>Tail No : "+ componentHistory.getComponent().getTailNo().toString();
				 popup = popup +"<br/>Installation Date : "+ componentHistory.getFromDate().toString();
				 popup = popup +"<br/>Installation Station : "+ componentHistory.getMaint_stn().toString();
				 popup = popup +"<br/>Installation Department : "+ componentHistory.getDept().toString();
//				 popup = popup +"<br/>HR_DTE : "+ componentHistory.getFromDate().toString();
//				 popup = popup +"<br/>HR_STA : "+ componentHistory.getMaint_stn().toString();
//				 popup = popup +"<br/>HR_DEPT : "+ componentHistory.getDept().toString();
//				 popup = popup +"<br/>HR_REASON : "+ componentHistory.getStatus_reason().toString();
//				 popup = popup +"<br/>HS_STA : "+ componentHistory.getComponent().getDescription().toString();
//				 popup = popup +"<br/>HS_DEPT : "+ componentHistory.getComponent().getDescription().toString();
//				 popup = popup +"<br/>HS_REPAIR_TYPE : "+ componentHistory.getComponent().getDescription().toString();
//				 popup = popup +"<br/>HS_REPAIR_ODR_NBR : "+ componentHistory.getComponent().getDescription().toString();
				 item.setTitle(popup);;
			 }
			 
			 item.setStart(startDate);
			 item.setEnd(endDate);
			 item.setGroup(componentHistory.getComponent().getComponentID().toString());
			 if(!componentHistory.getStatus().toString().contains("Repair")){
				 itemList.add(item);
			 }
		 }
		 
		 List<ComponentHistoryGroupVO> groupList = new ArrayList<ComponentHistoryGroupVO>(groupSet);
		 componentList.setGroupList(groupList);
		 componentList.setItemList(itemList);
		 return componentList;
		 
		
		
		
	}
}
