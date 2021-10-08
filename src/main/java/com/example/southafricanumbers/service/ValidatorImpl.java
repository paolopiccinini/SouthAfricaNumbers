package com.example.southafricanumbers.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.example.southafricanumbers.constants.Constants;
import com.example.southafricanumbers.constants.PhoneNumberStatus;
import com.example.southafricanumbers.domain.PhoneNumber;

@Service
public class ValidatorImpl implements IValidator {

	@Override
	public Map<PhoneNumberStatus, List<PhoneNumber>> checkPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		Map<PhoneNumberStatus, List<PhoneNumber>> mapPhoneNumbers = new HashMap<>();
		mapPhoneNumbers.put(PhoneNumberStatus.VALID, new ArrayList<>());
		mapPhoneNumbers.put(PhoneNumberStatus.CORRECTED, new ArrayList<>());
		mapPhoneNumbers.put(PhoneNumberStatus.REJECTED, new ArrayList<>());
		phoneNumbers.stream().forEach(phoneNumber -> {
			PhoneNumber phoneNumberValidated = checkPhoneNumber(phoneNumber);
			mapPhoneNumbers.get(phoneNumberValidated.getStatus()).add(phoneNumberValidated);
		});
		return mapPhoneNumbers;
	}

	@Override
	public PhoneNumber checkPhoneNumber(PhoneNumber phoneNumber) {
		String number = phoneNumber.getPhoneNumber();
		if(!StringUtils.isNumeric(number)) {
			phoneNumber.setDesc(Constants.ERROR_STRING_NOT_NUMERIC);
			phoneNumber.setStatus(PhoneNumberStatus.REJECTED);
		} else if(number.contains(Constants.INVALID_NUMBER_NINE) || number.contains(Constants.INVALID_NUMBER_ZERO)) {
			phoneNumber.setDesc(Constants.ERROR_INVALID_DIGIT);
			phoneNumber.setStatus(PhoneNumberStatus.REJECTED);
		} else if(number.length() == Constants.LENGHT_NUMBER_SOUTH_AFRICA_WITHOUT_PREFIX) {
			phoneNumber.setDesc(Constants.ADDED_PREFIX_SOUTH_AFRICA);
			phoneNumber.setPhoneNumber(Constants.PREFIX_SOUTH_AFRICA + number);
			phoneNumber.setStatus(PhoneNumberStatus.CORRECTED);
		} else if(number.length() == Constants.LENGHT_NUMBER_TEN && number.startsWith(Constants.PREFIX_ONLY_SEVEN)) {
			phoneNumber.setDesc(Constants.ADDED_PREFIX_SOUTH_AFRICA);
			phoneNumber.setPhoneNumber(Constants.PREFIX_ONLY_TWO + number);
			phoneNumber.setStatus(PhoneNumberStatus.CORRECTED);
		} else if(number.length() == Constants.LENGHT_NUMBER_SOUTH_AFRICA && number.startsWith(Constants.PREFIX_SOUTH_AFRICA)) {
			phoneNumber.setDesc(Constants.VALID_PHONE_NUMBER);
			phoneNumber.setStatus(PhoneNumberStatus.VALID);
		} else {
			phoneNumber.setDesc(Constants.ERROR_PHONE_NUMBER_NOT_VALID);
			phoneNumber.setStatus(PhoneNumberStatus.REJECTED);
		}
		return phoneNumber;
	}

}
