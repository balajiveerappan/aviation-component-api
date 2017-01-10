package com.aviation.poc.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.aviation.poc.entity.Component;
import com.aviation.poc.service.AviationComponentService;
import com.aviation.poc.util.ComponentConstants;
import com.aviation.poc.vo.ComponentReport;
import com.aviation.poc.vo.ComponentsPageVO;
import com.aviation.poc.vo.RemovalReportVO;

@RestController
public class AviationComponentController {

	@Autowired
	private AviationComponentService componentService;
	
	@RequestMapping(value = "/splashScreen", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Object> showSplashScreen(@RequestParam(required=false) String componentType) {
		String pattern = ComponentConstants.DATEFORMATNEW;
		String dataIntervalValue=getSplashDate();
		String[] dateInterval=dataIntervalValue.split(",");
	
		Date sDate=null;
		Date eDate=null;
		try {
			sDate =  new SimpleDateFormat(pattern).parse(dateInterval[0]);
			 eDate =  new SimpleDateFormat(pattern).parse(dateInterval[1]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		List<Object> componentRemovalRept =  componentService.getRemovedComponents(sDate, eDate, componentType);
		return componentRemovalRept;
	}
	
	@RequestMapping(value = "/loadComponent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Component> loadComponentData(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date start, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date end)
			throws ParseException {
		return componentService.getComponent(start, end);
	}

	

	@RequestMapping(value = "/removalReport", method = RequestMethod.GET/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
	@ResponseBody
	public com.aviation.poc.vo.ComponentReport removalReport(@RequestParam List<Long> componentIds,@RequestParam String fromDate) {
		return componentService.getComponents(componentIds,fromDate);
	}
	
	
	 
    
		@RequestMapping(value = "/getSplashDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String getSplashDate() {
			 Calendar cal = Calendar.getInstance();
	         cal.setTimeZone(TimeZone.getTimeZone("GMT"));
	         String toDate=getDate(cal);
	         cal.add(Calendar.YEAR, -2);
	         String fromDate=getDate(cal);
	         toDate=toDate.replaceAll("/", "-");
	         fromDate=fromDate.replaceAll("/", "-");
	         DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
	         String dateRange = null;
	     	try {
	     		Date frmDate= df.parse(fromDate);
	             Date tDate= df.parse(toDate);
	             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	             fromDate=formatter.format(frmDate);
	             toDate=formatter.format(tDate);
	             dateRange=fromDate;
	             dateRange=dateRange+", "+toDate;
	 	} catch (ParseException e) {
	 		e.printStackTrace();
	 	}
			return dateRange;
		}
		
		
		  public static String getDate(Calendar cal){
		        return "" + cal.get(Calendar.DATE) +"/" +
		                (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR);
		    }
		  

	    
	   /* private String getFormattedDate(String pattern, Date date){
	    	 SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	    	 return formatter.format(date);
	       
	         
	    }*/
		  
		  
		 
			
		  
		  @RequestMapping(value = "/navigationToRemoval", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		  public ComponentsPageVO  navigationToRemoval(@RequestParam(required=false) String actualData,@RequestParam(required=false) String dataType,@RequestParam(required=false) String fromDate,@RequestParam(required=false) String toDate){
				
			  ComponentsPageVO componentPageVO=new ComponentsPageVO();
			  String pageStatus=null;
			  System.out.println("in api navigationToRemoval"+actualData+"dsfds"+dataType);
			  
			  
			  String pattern = ComponentConstants.DATEFORMATNEW;
				//String dataIntervalValue=getSplashDate();
				//String[] date=dataIntervalValue.split(",");
				Date sDate=null;
				Date eDate=null;
				System.out.println("from date "+fromDate+" to date "+toDate);
				try {
					sDate =  new SimpleDateFormat(pattern).parse(fromDate);
					 eDate =  new SimpleDateFormat(pattern).parse(toDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				System.out.println("from date "+sDate+" to date "+eDate);
				List<Long> splashScreenComponentsId=new ArrayList<Long>();
				splashScreenComponentsId=componentService.getComponentsIdsSplashScreen(actualData, dataType, sDate, eDate);

				
				switch(dataType) {
				
				case "ATA" :
					break;
				
				case "CPN" :
					pageStatus="CPN-"+actualData;
					break;
				case "CSN" : 
					pageStatus="Copmany Serial No-"+actualData;
					break;
				case "Tail" : 
					pageStatus="TAIL-"+actualData;
					break;

				}	
				componentPageVO.setComponentIds(splashScreenComponentsId);
				componentPageVO.setPageStatus(pageStatus);
		   return componentPageVO;
				
		  }
		  
		  
		  
		  
	    
}
