package com.example.southafricanumbers.domain;

import com.example.southafricanumbers.constants.PhoneNumberStatus;
import com.opencsv.bean.CsvBindByPosition;

public class PhoneNumber {

	@CsvBindByPosition(position = 0)
	private String id;
	@CsvBindByPosition(position = 1)
	private String phoneNumber;
	private String desc;
	private PhoneNumberStatus status;

	public PhoneNumber() {

	}

	public PhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public PhoneNumberStatus getStatus() {
		return status;
	}

	public void setStatus(PhoneNumberStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhoneNumber [id=");
		builder.append(id);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", desc=");
		builder.append(desc);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
