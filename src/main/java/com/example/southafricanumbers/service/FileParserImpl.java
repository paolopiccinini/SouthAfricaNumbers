package com.example.southafricanumbers.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.southafricanumbers.domain.PhoneNumber;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class FileParserImpl implements IParser {

	@Override
	public List<PhoneNumber> getPhoneNumbers(File fileToParse) throws IllegalStateException, FileNotFoundException {
		return new CsvToBeanBuilder<PhoneNumber>(new FileReader(fileToParse))
				.withSkipLines(1)
				.withType(PhoneNumber.class)
				.build()
				.parse();
	}

	
}
