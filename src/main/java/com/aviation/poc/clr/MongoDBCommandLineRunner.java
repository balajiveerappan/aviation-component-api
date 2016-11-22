package com.aviation.poc.clr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

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

	private static Function<String, MasterData> mapRecordData = (line) -> {
		String[] data = line.split(",");
		return populateRecordData(data);
	};

	@SuppressWarnings("unchecked")
	@Override
	public void run(String... args) throws Exception {

		try (final InputStream inputStream = new FileInputStream(new File("/home/amit/data.csv"));
				final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
			bufferedReader.lines().skip(1).map(mapRecordData).forEach(reacordData -> {
				masterRepo.insert(reacordData);
			});
			
			mongoTemplate.getCollection("masterData").distinct("master").stream().forEach(masterId ->{
				MasterData masterData = masterRepo.findByMasterId((String)masterId);
				com.aviation.poc.entity.Component component = new com.aviation.poc.entity.Component();
				component.setAtaSystemNo(masterData.getHR_ATA());
				component.setClassification(masterData.getNOUN());
				component.setCmpySerialNo(masterData.getH_SERIAL());
				component.setCompanyPartNo(masterData.getH_RCN());
			});

			
		}
	}

	private static MasterData populateRecordData(String[] data) {
		final MasterData recordData = new MasterData();
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
		recordData.setHI_DTE(data[10]);
		recordData.setHI_STA(data[11]);
		recordData.setHI_DEPT(data[12]);
		if(data[13].equals("ACAQ")){
			System.out.println("Fiddle");
		}
		
		try {
			recordData.setHR_DTE(simpleDateFormat.parse(data[13]));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		recordData.setHS_REPAIR_DTE(data[23]);
		recordData.setHS_REPAIR_TYPE(data[24]);
		recordData.setHS_REPAIR_ODR_NBR(data[25]);
		recordData.setMaster(data[26]);
		return recordData;
	}
}
