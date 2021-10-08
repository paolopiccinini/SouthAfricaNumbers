package com.example.southafricanumbers.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.example.southafricanumbers.constants.Constants;
import com.example.southafricanumbers.constants.PhoneNumberStatus;
import com.example.southafricanumbers.domain.PhoneNumber;

public class ValidatorImplTest {
	
	ValidatorImpl validator = new ValidatorImpl();
	
	@Test
	public void checkPhoneNumberValid() {
		PhoneNumber phoneNumber = new PhoneNumber("27831234567");
		PhoneNumber result = validator.checkPhoneNumber(phoneNumber);
		assertEquals(phoneNumber.getPhoneNumber(), result.getPhoneNumber());
		assertEquals(Constants.VALID_PHONE_NUMBER, result.getDesc());
		assertEquals(PhoneNumberStatus.VALID, result.getStatus());
	}
	
	@Test
	public void checkPhoneNumberCorrected() {
		PhoneNumber phoneNumber = new PhoneNumber("831234567");
		PhoneNumber result = validator.checkPhoneNumber(phoneNumber);
		assertEquals(Constants.PREFIX_SOUTH_AFRICA + phoneNumber.getPhoneNumber(), Constants.PREFIX_SOUTH_AFRICA + result.getPhoneNumber());
		assertEquals(Constants.ADDED_PREFIX_SOUTH_AFRICA, result.getDesc());
		assertEquals(PhoneNumberStatus.CORRECTED, result.getStatus());
	}
	
	@Test
	public void checkPhoneNumberCorrectedStartsWith7And10Lenght() {
		PhoneNumber phoneNumber = new PhoneNumber("7123456787");
		PhoneNumber result = validator.checkPhoneNumber(phoneNumber);
		assertEquals(Constants.ADDED_PREFIX_SOUTH_AFRICA, result.getDesc());
		assertEquals(PhoneNumberStatus.CORRECTED, result.getStatus());
	}
	
	@Test
	public void checkPhoneNumberRejectedNotNumeric() {
		PhoneNumber phoneNumber = new PhoneNumber("ciao");
		PhoneNumber result = validator.checkPhoneNumber(phoneNumber);
		assertEquals(Constants.ERROR_STRING_NOT_NUMERIC, result.getDesc());
		assertEquals(PhoneNumberStatus.REJECTED, result.getStatus());
	}
	
	@Test
	public void checkPhoneNumberRejectedInvalidDigit() {
		PhoneNumber phoneNumber = new PhoneNumber("09");
		PhoneNumber result = validator.checkPhoneNumber(phoneNumber);
		assertEquals(Constants.ERROR_INVALID_DIGIT, result.getDesc());
		assertEquals(PhoneNumberStatus.REJECTED, result.getStatus());
	}
	
	@Test
	public void checkPhoneNumberRejectedNumberTooLong() {
		PhoneNumber phoneNumber = new PhoneNumber("123456787654321");
		PhoneNumber result = validator.checkPhoneNumber(phoneNumber);
		assertEquals(Constants.ERROR_PHONE_NUMBER_NOT_VALID, result.getDesc());
		assertEquals(PhoneNumberStatus.REJECTED, result.getStatus());
	}
	
	@Test
	public void checkPhoneNumberRejectedNumberCorrectLenghtButNotStartWith27() {
		PhoneNumber phoneNumber = new PhoneNumber("12345678123");
		PhoneNumber result = validator.checkPhoneNumber(phoneNumber);
		assertEquals(Constants.ERROR_PHONE_NUMBER_NOT_VALID, result.getDesc());
		assertEquals(PhoneNumberStatus.REJECTED, result.getStatus());
	}
	
	@Test
	public void checkPhoneNumbersValidCorrectedRejected() {
		PhoneNumber phoneNumber1 = new PhoneNumber("27831234567");
		PhoneNumber phoneNumber2 = new PhoneNumber("831234567");
		PhoneNumber phoneNumber3 = new PhoneNumber("123456787654321");
		List<PhoneNumber> phoneNumbers = new ArrayList<>();
		phoneNumbers.add(phoneNumber1);
		phoneNumbers.add(phoneNumber2);
		phoneNumbers.add(phoneNumber3);
		Map<PhoneNumberStatus, List<PhoneNumber>> mapPhoneNumbers = validator.checkPhoneNumbers(phoneNumbers);
		assertEquals(1, mapPhoneNumbers.get(PhoneNumberStatus.VALID).size());
		assertEquals(1, mapPhoneNumbers.get(PhoneNumberStatus.CORRECTED).size());
		assertEquals(1, mapPhoneNumbers.get(PhoneNumberStatus.REJECTED).size());
	}
	
}
