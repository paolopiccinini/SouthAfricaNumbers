package com.example.southafricanumbers.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.example.southafricanumbers.constants.PhoneNumberStatus;
import com.example.southafricanumbers.domain.PhoneNumber;

public interface IWriter {
	
	void writecsvFiles(File fileOutput, Map<PhoneNumberStatus, List<PhoneNumber>> mapPhoneNumbers) throws IOException;

}
