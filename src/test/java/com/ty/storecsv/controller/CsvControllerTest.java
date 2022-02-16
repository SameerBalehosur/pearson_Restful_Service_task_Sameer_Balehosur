package com.ty.storecsv.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ty.storecsv.model.CsvFile;
import com.ty.storecsv.service.CsvService;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CsvControllerTest {

	ObjectMapper mapper=new ObjectMapper();

	@MockBean
	private CsvService csvService;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	void setUp() throws Exception {
		mockMvc	= MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void testFindById() throws UnsupportedEncodingException, Exception {
		Date date=new SimpleDateFormat("dd-mm-yyyy").parse("10-04-1996");
		CsvFile csvFile=new CsvFile("543", "dafs", "bang", "Kar", date);

		when(csvService.fetchDataById(Mockito.anyString())).thenReturn(csvFile);
		String contentAsString = mockMvc.perform(get("/api/csv/byId/543")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		CsvFile readValue = mapper.readValue(contentAsString, CsvFile.class);
		assertEquals("543", readValue.getStoreId());
	}
	@Test
	void testFetchByCityOrDate() throws UnsupportedEncodingException, Exception {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("12/10/1998");
		List<CsvFile> list = new ArrayList<CsvFile>();
		CsvFile file = new CsvFile("hi", "2e", "me", "ss", date);
		list.add(file);
		when(csvService.fetchByCityOrDate(Mockito.anyString())).thenReturn(list);
		String contentAsString = mockMvc.perform(get("/api/csv/byCityOrDate/city")).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		List<CsvFile> readValue2 = mapper.readValue(contentAsString, new TypeReference<List<CsvFile>>() {});
		assertEquals("hi", readValue2.get(0).getStoreId());
	}

}
