package com.pradeep.menu.bean.to.user;

public class CustomerTO extends UserTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8181420810209220717L;
	@Override
	public void setUserType(String userType) {
		super.setUserType("CUSTOMER");
	}
}
