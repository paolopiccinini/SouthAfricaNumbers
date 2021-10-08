package com.example.southafricanumbers.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.southafricanumbers.constants.PhoneNumberStatus;
import com.example.southafricanumbers.domain.PhoneNumber;
import com.opencsv.CSVWriter;

@Service
public class WriterImpl implements IWriter {

	@Override
	public void writecsvFiles(File fileOutput, Map<PhoneNumberStatus, List<PhoneNumber>> mapPhoneNumbers) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(fileOutput), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		writer.writeAll(createCsvData(mapPhoneNumbers));
		writer.close();
		System.out.println("Writed file " + fileOutput.getAbsolutePath());
	}
	
	private List<String[]> createCsvData(Map<PhoneNumberStatus, List<PhoneNumber>> mapPhoneNumbers) {
		List<PhoneNumber> phoneNumbersValid = mapPhoneNumbers.get(PhoneNumberStatus.VALID);
		List<PhoneNumber> phoneNumbersCorrected = mapPhoneNumbers.get(PhoneNumberStatus.CORRECTED);
		List<PhoneNumber> phoneNumbersRejected = mapPhoneNumbers.get(PhoneNumberStatus.REJECTED);
		List<String[]> result = new ArrayList<>();
		result.addAll(prepareListForCsvFile(phoneNumbersValid));
		result.addAll(prepareListForCsvFile(phoneNumbersCorrected));
		result.addAll(prepareListForCsvFile(phoneNumbersRejected));
		return result;
	}
	
	private List<String[]> prepareListForCsvFile(List<PhoneNumber> phoneNumbers) {
		List<String[]> result = new ArrayList<>();
		phoneNumbers.stream().forEach(phoneNumber -> {
			String[] strings = new String[4];
			strings[0] = phoneNumber.getId();
			strings[1] = phoneNumber.getPhoneNumber();
			strings[2] = phoneNumber.getDesc();
			strings[3] = phoneNumber.getStatus().name();
			result.add(strings);
		});
		return result;
	}

}
