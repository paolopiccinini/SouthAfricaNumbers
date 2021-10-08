package com.example.southafricanumbers.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.example.southafricanumbers.constants.Constants;
import com.example.southafricanumbers.constants.PhoneNumberStatus;
import com.example.southafricanumbers.domain.PhoneNumber;

public class WriterImplTest {
	
	WriterImpl writer = new WriterImpl();
	
	@Test
	public void writecsvFilesToResources() throws IOException {
		File fileOutput = new File("src/test/resources/output.csv");
		PhoneNumber phoneNumber1 = new PhoneNumber();
		phoneNumber1.setDesc(Constants.VALID_PHONE_NUMBER);
		phoneNumber1.setId("1");
		phoneNumber1.setPhoneNumber("27831234567");
		phoneNumber1.setStatus(PhoneNumberStatus.VALID);
		PhoneNumber phoneNumber2 = new PhoneNumber();
		phoneNumber2.setDesc(Constants.ADDED_PREFIX_SOUTH_AFRICA);
		phoneNumber2.setId("2");
		phoneNumber2.setPhoneNumber("27831234567");
		phoneNumber2.setStatus(PhoneNumberStatus.CORRECTED);
		PhoneNumber phoneNumber3 = new PhoneNumber();
		phoneNumber3.setDesc(Constants.ERROR_STRING_NOT_NUMERIC);
		phoneNumber3.setId("3");
		phoneNumber3.setPhoneNumber("ciao");
		phoneNumber3.setStatus(PhoneNumberStatus.REJECTED);
		List<PhoneNumber> phoneNumbersValid = new ArrayList<>();
		phoneNumbersValid.add(phoneNumber1);
		List<PhoneNumber> phoneNumbersCorrected = new ArrayList<>();
		phoneNumbersValid.add(phoneNumber2);
		List<PhoneNumber> phoneNumbersRejected = new ArrayList<>();
		phoneNumbersValid.add(phoneNumber3);
		Map<PhoneNumberStatus, List<PhoneNumber>> mapPhoneNumbers = new HashMap<>();
		mapPhoneNumbers.put(PhoneNumberStatus.VALID, phoneNumbersValid);
		mapPhoneNumbers.put(PhoneNumberStatus.CORRECTED, phoneNumbersCorrected);
		mapPhoneNumbers.put(PhoneNumberStatus.REJECTED, phoneNumbersRejected);
		writer.writecsvFiles(fileOutput, mapPhoneNumbers);
		LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(fileOutput));
		lineNumberReader.skip(Long.MAX_VALUE);
		int lines = lineNumberReader.getLineNumber();
		lineNumberReader.close();
		assertEquals(true, fileOutput.exists());
		assertEquals(3, lines);
	}

}
