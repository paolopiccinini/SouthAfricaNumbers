package com.example.southafricanumbers.constants;

public class Constants {
	
	private Constants() {
		
	}

	public static final int LENGHT_NUMBER_SOUTH_AFRICA = 11;
	public static final int LENGHT_NUMBER_SOUTH_AFRICA_WITHOUT_PREFIX = 9;
	public static final int LENGHT_NUMBER_TEN = 10;
	public static final String PREFIX_SOUTH_AFRICA = "27";
	public static final String PREFIX_ONLY_SEVEN = "7";
	public static final String PREFIX_ONLY_TWO = "2";
	public static final String INVALID_NUMBER_ZERO = "0";
	public static final String INVALID_NUMBER_NINE = "9";
	public static final String QUIT_CONSOLE = "quit";
	public static final String READ_FROM_FILE = "1";
	public static final String CHECK_FROM_CONSOLE = "2";
	public static final String ADDED_PREFIX_SOUTH_AFRICA = "The phone number was not valid corrected adding the south africa prefix";
	public static final String VALID_PHONE_NUMBER = "The phone number is valid";
	public static final String ERROR_STRING_NOT_NUMERIC = "Error the phone number is not a number";
	public static final String ERROR_PHONE_NUMBER_NOT_VALID = "The phone number is not valid is either too log or not starting with " + Constants.PREFIX_SOUTH_AFRICA;
	public static final String ERROR_INVALID_DIGIT = "The phone number contains either " + Constants.INVALID_NUMBER_NINE + " or " + Constants.INVALID_NUMBER_ZERO;
}
