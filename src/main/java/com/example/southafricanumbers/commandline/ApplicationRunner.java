package com.example.southafricanumbers.commandline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.southafricanumbers.constants.Constants;
import com.example.southafricanumbers.constants.PhoneNumberStatus;
import com.example.southafricanumbers.domain.PhoneNumber;
import com.example.southafricanumbers.service.IParser;
import com.example.southafricanumbers.service.IValidator;
import com.example.southafricanumbers.service.IWriter;

@Component
public class ApplicationRunner implements CommandLineRunner {
	
	private static Logger LOG = LoggerFactory.getLogger(ApplicationRunner.class);
	
	@Autowired
	IParser parser;
	
	@Autowired
	IValidator validator;
	
	@Autowired
	IWriter writer;

	@Override
	public void run(String... args) throws Exception {
		String input = null;
		Scanner in = new Scanner(System.in);
		while(!Constants.QUIT_CONSOLE.equalsIgnoreCase(input)) {
			input = printMenuAndGetInput(in);
			if(Constants.READ_FROM_FILE.equals(input)) {
				manageReadFromFile(in);
			} else if (Constants.CHECK_FROM_CONSOLE.equals(input)) {
				manageInputFromConsole(in);
			}
				
		}
		in.close();
	}

	private void manageInputFromConsole(Scanner in) {
		System.out.println("Insert a number");
		String number = in.nextLine();
		PhoneNumber phoneNumber = new PhoneNumber(number);
		phoneNumber = validator.checkPhoneNumber(phoneNumber);
		System.out.println(phoneNumber.getPhoneNumber() + ", " + phoneNumber.getDesc());
	}

	private void manageReadFromFile(Scanner in) {
		System.out.println("Please insert the path of the csv file");
		String path = in.nextLine();
		System.out.println("Please insert the path for the output files");
		String pathOutput = in.nextLine();
		try {
			File file = new File(path);
			File fileOutput = new File(pathOutput);
			List<PhoneNumber> phoneNumbers = parser.getPhoneNumbers(file);
			Map<PhoneNumberStatus, List<PhoneNumber>> mapPhoneNumbers = validator.checkPhoneNumbers(phoneNumbers);
			writer.writecsvFiles(fileOutput, mapPhoneNumbers);
		} catch (IllegalStateException | FileNotFoundException e) {
			System.out.println("Error during parsing of the file please insert a valid path and a valid csv in input format: id,sms_phone");
		} catch (IOException e) {
			System.out.println("Error during writing the output file, please insert a valid file name and path");
		}
	}

	private String printMenuAndGetInput(Scanner in) {
		String input;
		System.out.println("1) Enter " + Constants.READ_FROM_FILE + " to read numbers from file");
		System.out.println("2) Enter " + Constants.CHECK_FROM_CONSOLE + " to read numbers from file");
		System.out.println("3) Enter " + Constants.QUIT_CONSOLE + " to read numbers from file");
		input = in.nextLine();
		return input;
	}

}
