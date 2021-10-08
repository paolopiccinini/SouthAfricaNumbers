package com.example.southafricanumbers.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.southafricanumbers.domain.PhoneNumber;

public class FileParserImplTest {
	
	FileParserImpl parser = new FileParserImpl();
	
	@Test
	public void getPhoneNumbersFromFile() throws IllegalStateException, FileNotFoundException {
		File fileToParse = new File("src/test/resources/testNumbers.csv");
		List<PhoneNumber> phoneNumbers = parser.getPhoneNumbers(fileToParse);
		assertEquals(1000, phoneNumbers.size());
	}
	
	@Test
	public void getPhoneNumbersThrowsFileNotFoundException() throws IllegalStateException, FileNotFoundException {
		File fileToParse = new File("src/test/resources/test.csv");
		assertThrows(FileNotFoundException.class, () -> parser.getPhoneNumbers(fileToParse));
	}

}
