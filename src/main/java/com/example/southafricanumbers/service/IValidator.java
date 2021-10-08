package com.example.southafricanumbers.service;

import java.util.List;
import java.util.Map;

import com.example.southafricanumbers.constants.PhoneNumberStatus;
import com.example.southafricanumbers.domain.PhoneNumber;

public interface IValidator {

	Map<PhoneNumberStatus, List<PhoneNumber>> checkPhoneNumbers(List<PhoneNumber> phoneNumbers);
	
	PhoneNumber checkPhoneNumber(PhoneNumber phoneNumber);

}
