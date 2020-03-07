package com.capgemini.storesmanagementsystem.exception;

public class AddingManufacturerFailedException extends RuntimeException {
	String message;
	public   AddingManufacturerFailedException() {
		this.message="Adding manufacturer failed";
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
