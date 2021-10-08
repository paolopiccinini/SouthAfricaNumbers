package com.example.southafricanumbers.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.example.southafricanumbers.domain.PhoneNumber;

public interface IParser {
	
	List<PhoneNumber> getPhoneNumbers(File fileToParse) throws IllegalStateException, FileNotFoundException;
	
}
