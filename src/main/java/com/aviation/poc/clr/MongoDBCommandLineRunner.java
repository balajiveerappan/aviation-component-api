package com.aviation.poc.clr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.Function;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.aviation.poc.entity.ComponentHistory;
import com.aviation.poc.repository.ComponentRepository;
import com.aviation.poc.repository.MasterDataRepository;
import com.aviation.poc.vo.MasterData;

@Component
public class MongoDBCommandLineRunner implements CommandLineRunner {

	@Autowired
	private MasterDataRepository masterRepo;

	@Autowired
	private ComponentRepository componentRepository;

	@Autowired
	private MongoTemplate mongoTemplate;
	

	@Autowired
	private MongoOperations mongoOperations;

	private static Function<String, MasterData> mapRecordData = (line) -> {
		String[] data = line.split(",");
		return populateRecordData(data);
	};

	@SuppressWarnings("unchecked")
	@Override
	public void run(String... args) throws Exception {

		componentRepository.deleteAll();
		masterRepo.deleteAll();
		
		try (final InputStream inputStream = new FileInputStream(new File("/home/amit/data.csv"));
				final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
			bufferedReader.lines().skip(1).map(mapRecordData).forEach(reacordData -> {
				masterRepo.insert(reacordData);
			});

			// Insert Data into Component Table
			mongoTemplate.getCollection("masterData").distinct("master").stream().forEach(masterId -> {
				MasterData masterData = masterRepo.findByMasterId((String) masterId);
				com.aviation.poc.entity.Component component = componentRepository.findByMasterId((String) masterId);

				if (null == component) {
					component = new com.aviation.poc.entity.Component();
					component.setAtaSystemNo(masterData.getHR_ATA());
					component.setClassification(masterData.getNOUN());
					component.setCmpySerialNo(masterData.getH_SERIAL());
					component.setCompanyPartNo(masterData.getH_RCN());
					component.setMaster(masterData.getMaster());
					componentRepository.save(component);
				}
			});

				mongoOperations.findAll(MasterData.class)
						.stream().forEach(masterData -> {
							System.out.println("Inserting Componet History:::::::::");
								mongoOperations.upsert(Query.query(Criteria.where("master").is(masterData.getMaster())), 
										new Update().push("history", populateComponentHistory(masterData)), Component.class,"components");
						}
				);
		}
	}

	private static MasterData populateRecordData(String[] data) throws RuntimeException {
		final MasterData recordData = new MasterData();
		
		try{
		recordData.setH_RCN(data[0]);
		;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		recordData.setHR_MPN(data[1]);
		recordData.setH_SERIAL(data[2]);
		recordData.setHR_MSN(data[3]);
		recordData.setNOUN(data[4]);
		recordData.setDESCRIPTION(data[5]);
		recordData.setH_ACN(data[6]);
		recordData.setFLEET(data[7]);
		recordData.setFLEET_MODEL_CD(data[8]);
		recordData.setH_POS(data[9]);
		if(StringUtils.isNotBlank(data[10])){
			recordData.setHI_DTE(simpleDateFormat.parse(data[10]));
		}
		recordData.setHI_STA(data[11]);
		recordData.setHI_DEPT(data[12]);
		if(StringUtils.isNotBlank(data[13])){

		recordData.setHR_DTE(simpleDateFormat.parse(data[13]));
		}

		recordData.setHR_STA(data[14]);
		recordData.setHR_DEPT(data[15]);
		recordData.setHR_REASON(data[16]);
		recordData.setHR_TSI(data[17]);
		recordData.setHR_CSI(data[18]);
		recordData.setHR_ATA(data[19]);
		recordData.setHR_D_NBR(data[20]);
		recordData.setHS_STA(data[21]);
		recordData.setHS_DEPT(data[22]);
		if(StringUtils.isNotBlank(data[23])){

		recordData.setHS_REPAIR_DTE(simpleDateFormat.parse(data[23]));
		}
		recordData.setHS_REPAIR_TYPE(data[24]);
		recordData.setHS_REPAIR_ODR_NBR(data[25]);
		recordData.setMaster(data[26]);
		}catch (ParseException e) {
			throw new RuntimeException("Unable to Parse date from the excel sheet");
		}
		return recordData;
	}
	
	public ComponentHistory populateComponentHistory(final MasterData masterData){
		final ComponentHistory componentHistory = new ComponentHistory();
				
		componentHistory.setInstallationDate(masterData.getHI_DTE());
		componentHistory.setRepairDate(masterData.getHS_REPAIR_DTE());
		componentHistory.setRemovalDate(masterData.getHR_DTE());
		componentHistory.setInstallationDept(masterData.getHI_DEPT());
		componentHistory.setRemovalDept(masterData.getHR_DEPT());
		componentHistory.setRepairDept(masterData.getHS_DEPT());
		componentHistory.setMaintStnInstallation(masterData.getHI_STA());
		componentHistory.setMaintStnRemoval(masterData.getHR_STA());
		componentHistory.setMaintStnRepair(masterData.getHS_STA());
		componentHistory.setDiscrepencyNoRemoval(masterData.getHR_D_NBR());
		componentHistory.setStatusReasonRemoval(masterData.getHR_REASON());
		componentHistory.setPositionComponent(masterData.getH_POS());
		return componentHistory;
	}
	
}
