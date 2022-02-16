package com.ty.storecsv.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.ty.storecsv.model.CsvFile;

@Service
public class CsvService {
	@SuppressWarnings("resource")
	public CsvFile fetchDataById(String sid){
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader("src/main/resources/sam.csv"));
			CSVParser csvParser=new CSVParser(bufferedReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			Iterable<CSVRecord> iterable=csvParser.getRecords();
			if(iterable!=null) {
				for (CSVRecord csvRecord : iterable) {
					Date date=new SimpleDateFormat("dd-mm-yyyy").parse(csvRecord.get("Opened Date"));
					CsvFile csvFile=new CsvFile(csvRecord.get("Store Id"),csvRecord.get("Post Code"),csvRecord.get("City"),csvRecord.get("Address"),date);
					if(csvFile.getStoreId().equals(sid)) {
						return csvFile;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("resource")
	public List<CsvFile> fetchByCityOrDate(String unique){
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader("src/main/resources/sam.csv"));
			CSVParser csvParser=new CSVParser(bufferedReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			Iterable<CSVRecord> iterable=csvParser.getRecords();
			List<CsvFile> list=new ArrayList<>();
			for (CSVRecord csvRecord : iterable) {
				Date date=new SimpleDateFormat("dd-mm-yyyy").parse(csvRecord.get("Opened Date"));
				CsvFile csvFile=new CsvFile(csvRecord.get("Store Id"),csvRecord.get("Post Code"),csvRecord.get("City"),csvRecord.get("Address"),date);
				if(unique.equalsIgnoreCase("city")) {
					list.add(csvFile);
				}else if(unique.equalsIgnoreCase("Opened Date")) {
					list.add(csvFile);
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if(unique.equalsIgnoreCase("city")) {
					return list.stream().sorted((var1,var2)->var1.getCity().compareTo(var2.getCity())).collect(Collectors.toList());
				}else if(unique.equalsIgnoreCase("Opened Date")) {
					return list.stream().sorted((var1,var2)->var1.getDate().compareTo(var2.getDate())).collect(Collectors.toList());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
