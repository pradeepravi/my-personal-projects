package com.pradeep.menu.util.exception;

public class WebRecommendationsException extends Exception {
	/** 
	 * 
	 */
	private static final long serialVersionUID = -5985644015063861269L;
	
	private String message ;  
	public WebRecommendationsException (String message){
		super();
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
	
}
