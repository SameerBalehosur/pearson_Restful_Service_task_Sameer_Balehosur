package com.ty.storecsv.controller;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.storecsv.model.CsvFile;
import com.ty.storecsv.service.CsvService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/csv/")
@Slf4j
public class CsvController {
	@Autowired
	CsvService csvService;
	
	@SuppressWarnings({"rawtypes", "unchecked" })
	@GetMapping("/byId/{sid}")
	public ResponseEntity<?> findById(@PathVariable("sid") String id){
		CsvFile fetchDataById = csvService.fetchDataById(id);
		if(fetchDataById!=null) {
			log.info("Displaying the Stored Data",fetchDataById);
			return new ResponseEntity(fetchDataById,HttpStatus.OK);
		}else {
			log.error("No Data stored found");
			return new ResponseEntity("Data fetch failed",HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/byCityOrDate/{unique}")
	public ResponseEntity<?> fetchByCityOrDate(@PathVariable("unique") String unique){
		List<CsvFile> fetchByCityOrDate = csvService.fetchByCityOrDate(unique);
		if(fetchByCityOrDate!=null) {
			log.info("Displaying the all Stores details w.r.t city or Date",fetchByCityOrDate);
			return new ResponseEntity<>(fetchByCityOrDate,HttpStatus.OK);
		}else {
			log.error("No Data stored found");
			return new ResponseEntity<>("Data fetch failed",HttpStatus.NOT_FOUND);
		}
	}

}
