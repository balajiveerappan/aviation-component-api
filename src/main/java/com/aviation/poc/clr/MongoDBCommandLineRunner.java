package com.aviation.poc.clr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.aviation.poc.entity.ComponentHistory;
import com.aviation.poc.repository.ComponentHistoryRepository;
import com.aviation.poc.vo.RecordData;

public class MongoDBCommandLineRunner implements CommandLineRunner{

	@Autowired
	private ComponentHistoryRepository historyRepo;
	
	private static Function<String , RecordData> mapRecordData= (line) -> {
		String[] data = line.split(",");
		return populateRecordData(data);
	};

	private static Consumer<RecordData> insertData = reacordData ->{
		
	};
	@Override
	public void run(String... arg0) throws Exception {
	
		final InputStream inputStream = new FileInputStream(new File("C:\\data.csv"));
		final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		bufferedReader.lines().skip(1).map(mapRecordData).map(recordData -> )
	}

	private static RecordData populateRecordData(String[] data){
		RecordData recordData = new RecordData();
		recordData.setAtaSystemNumber(data[19]);
		recordData.setClassification(data[4]);
	}
}
