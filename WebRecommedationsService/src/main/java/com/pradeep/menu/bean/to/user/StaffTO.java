package com.pradeep.menu.bean.to.user;

import java.util.Date;

public class StaffTO extends UserTO {

	/**
	 * 
	 */
	 
	private static final long serialVersionUID = 1608226908611740972L;

	public StaffTO(){
		super();
	}
	public StaffTO(String firstName, String lastName, String middleName, Date dob, String mobileNumber,
			boolean isActive, char sex, String userType, String email) {
		super( firstName, lastName, middleName, dob, mobileNumber, isActive, sex, userType,email);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setUserType(String userType) {
		super.setUserType("STAFF");
	}
	@Override
	public String toString() {
		return "StaffTO [getId()=" + getId() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
				+ ", getMiddleName()=" + getMiddleName() + ", getDob()=" + getDob() + ", getMobileNumber()="
				+ getMobileNumber() + ", isActive()=" + isActive() + ", getSex()=" + getSex() + ", getUserType()="
				+ getUserType() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
