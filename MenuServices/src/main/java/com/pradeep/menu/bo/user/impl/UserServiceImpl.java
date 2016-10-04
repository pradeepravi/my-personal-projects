package com.pradeep.menu.bo.user.impl;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pradeep.menu.bean.entity.User;
import com.pradeep.menu.bean.to.user.StaffTO;
import com.pradeep.menu.bean.to.user.UserTO;
import com.pradeep.menu.bo.user.UserService;
import com.pradeep.menu.dao.user.UserDAO;
import com.pradeep.menu.dao.user.UserTypeDAO;

@Component("userServiceImpl")
public class UserServiceImpl implements UserService {

	final Logger log = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	UserDAO userDAO;

	@Autowired
	UserTypeDAO userTypeDAO;

	@Override
	public UserTO save(UserTO userTo) {
		log.debug("StaffServiceImpl : SAVE");
		User user = this.getPopulatedUser(userTo);
		user = userDAO.save(user);
		// userTo.setUse

		return userTo;
	}

	@Override
	public UserTO update(UserTO userTo) {

		log.debug("StaffServiceImpl : UPDATE");

		User user = this.getPopulatedUser(userTo);
		user = userDAO.update(user);
		return userTo;

	}

	@Override
	public boolean delete(UserTO userTo) {

		User user = this.getPopulatedUser(userTo);
		boolean flag = userDAO.delete(user);
		return flag;

	}

	private StaffTO getPopulatedUserTO(User user) {
		StaffTO staff = null;
		log.debug("StaffServiceImpl : getPopulatedUserTO");

		if (user != null) {
			staff = new StaffTO();
			staff.setId(user.getId());
			staff.setFirstName(user.getFirstName());
			staff.setLastName(user.getLastName());
			staff.setMiddleName(user.getMiddleName());
			staff.setMobileNumber(user.getMobileNumber());
			staff.setSex(user.getSex());
			staff.setEmail(user.getEmail());
			staff.setDob(user.getDob());
			staff.setCreatedDate(user.getCreatedDate());
			staff.setUserType(user.getUserType().getType());
		}

		return staff;
	}

	private User getPopulatedUser(UserTO userTO) {
		User user = null;

		log.debug("StaffServiceImpl : getPopulatedUser");

		if (userTO != null) {
			user = new User();
			user.setId(userTO.getId());
			user.setFirstName(userTO.getFirstName());
			user.setLastName(userTO.getLastName());
			user.setMiddleName(userTO.getMiddleName());
			user.setMobileNumber(userTO.getMobileNumber());
			user.setSex(userTO.getSex());
			user.setDob(userTO.getDob());
			user.setCreatedDate(new GregorianCalendar().getTime());// Todays
																	// dats
			user.setUserType(userTypeDAO.getUserType(userTO.getUserType()));
			user.setEmail(userTO.getEmail());
		}

		return user;
	}

	@Override
	public List<UserTO> fetchAllUser() {
		log.debug("StaffServiceImpl : fetchAllStaff");
		final List<User> users = userDAO.getAllUsers();

		List<UserTO> staffs = new LinkedList<UserTO>();
		for (User user : users) {
			UserTO staff = getPopulatedUserTO(user);
			staffs.add(staff);
		}

		log.debug("StaffServiceImpl : fetchAllStaff : LIST [" + staffs + "]");

		return staffs;
	}

	@Override
	public UserTO findUserByID(long userID) {
		User user = userDAO.getUser(new User(userID));
		final StaffTO staff = getPopulatedUserTO(user);
		return staff;
	}

	@Override
	public UserTO findUserByEmail(String email) {
		log.debug("StaffServiceImpl : findUserByEmail : [" + email + "]");
		final User user = new User();
		user.setEmail(email);
		User userRet = userDAO.getUser(user);
		UserTO userTo = null;
		if (userRet != null) {
			log.debug("StaffServiceImpl : findUserByEmail : User [" + userRet.getId() + "]");
			userTo = getPopulatedUserTO(userRet);
		}
		return userTo;
	}

}
