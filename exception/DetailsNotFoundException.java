package com.capgemini.storesmanagementsystem.exception;

public class DetailsNotFoundException extends RuntimeException {
	String message;
	public DetailsNotFoundException(){
		this.message="DetailsNotFoundException....";
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
