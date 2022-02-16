package com.ty.storecsv.model;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class CsvFileTest {
	String json="{\"storeId\":\"987\",\"postCode\":\"ssdd\",\"city\":\"sss\",\"address\":\"ban\",\"date\":821212440000}";
	ObjectMapper mapper=new ObjectMapper();

	@Test
	void serializeTest() throws ParseException, JsonProcessingException {
		//This is to get the JSON data as we need it for storing
//		Date date=new SimpleDateFormat("dd-mm-yyyy").parse("10-04-1996");
//		CsvFile csvFile=new CsvFile("987", "ssdd", "sss", "ban", date);
//				
//		String writeValueAsString = mapper.writeValueAsString(csvFile);
//		System.out.println(writeValueAsString);
		
		CsvFile readValue = mapper.readValue(json, CsvFile.class);
		String writeValueAsString = mapper.writeValueAsString(readValue);
		assertEquals(json, writeValueAsString);
	}
	@Test
	void deserializeTest() throws JsonMappingException, JsonProcessingException {
		CsvFile readValue = mapper.readValue(json, CsvFile.class);
		assertEquals("ban", readValue.getAddress());
		
	}

}
