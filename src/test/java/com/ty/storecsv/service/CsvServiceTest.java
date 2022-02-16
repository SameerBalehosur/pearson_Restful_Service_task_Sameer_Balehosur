package com.ty.storecsv.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ty.storecsv.model.CsvFile;
@ExtendWith(MockitoExtension.class)
class CsvServiceTest {

	@InjectMocks
	private CsvService service;

	@Test
	void testFetchDataById() throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("12/10/1998");
		CsvFile file = new CsvFile("1525eec4-7bed-4597-bf5a-e06fcede5f4f", "sm", "gm", "tt", date);
		CsvFile fetchDataById = service.fetchDataById(file.getStoreId());
		assertEquals("1525eec4-7bed-4597-bf5a-e06fcede5f4f", fetchDataById.getStoreId());
	}

	@Test
	void testFetchByCityOrDate() throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("12/10/1998");
		CsvFile file = new CsvFile("1525eec4-7bed-4597-bf5a-e06fcede5f4f", "sm", "gm", "tt", date);
		List<CsvFile> list = new ArrayList<CsvFile>();
		list.add(file);
		assertEquals("1525eec4-7bed-4597-bf5a-e06fcede5f4f",
		service.fetchByCityOrDate("city").get(0).getStoreId());

	}

}
