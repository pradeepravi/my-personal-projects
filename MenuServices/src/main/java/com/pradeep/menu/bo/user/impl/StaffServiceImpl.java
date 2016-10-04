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
import com.pradeep.menu.bo.user.StaffService;
import com.pradeep.menu.dao.user.impl.UserDAOImpl;
import com.pradeep.menu.dao.user.impl.UserTypeDAOImpl;

@Component("staffServiceImpl")
public class StaffServiceImpl implements StaffService {
	final Logger log = LogManager.getLogger(StaffServiceImpl.class);

	@Autowired
	UserDAOImpl userDAO;

	@Autowired
	UserTypeDAOImpl userTypeDAO;

	@Override
	public StaffTO findStaffByID(long staffID) {
		User user = userDAO.getUser(new User(staffID));
		final StaffTO staff = getPopulatedStaffTO(user);
		return staff;
	}

	@Override
	public boolean save(StaffTO staff) {
		log.debug("StaffServiceImpl : SAVE");
		User user = this.getPopulatedUser(staff);
		user = userDAO.save(user);
		return true;
	}

	@Override
	public boolean update(StaffTO staff) {

		log.debug("StaffServiceImpl : UPDATE");

		User user = this.getPopulatedUser(staff);
		user = userDAO.update(user);
		return true;

	}

	@Override
	public boolean delete(StaffTO staff) {

		User user = this.getPopulatedUser(staff);
		boolean flag = userDAO.delete(user);
		return flag;

	}

	private StaffTO getPopulatedStaffTO(User user) {
		StaffTO staff = null;
		log.debug("StaffServiceImpl : getPopulatedStaffTO");

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

	private User getPopulatedUser(StaffTO staff) {
		User user = null;

		log.debug("StaffServiceImpl : getPopulatedUser");

		if (staff != null) {
			user = new User();
			user.setId(staff.getId());
			user.setFirstName(staff.getFirstName());
			user.setLastName(staff.getLastName());
			user.setMiddleName(staff.getMiddleName());
			user.setMobileNumber(staff.getMobileNumber());
			user.setSex(staff.getSex());
			user.setDob(staff.getDob());
			user.setCreatedDate(new GregorianCalendar().getTime());// Todays
																	// date
			user.setUserType(userTypeDAO.getUserType(staff.getUserType()));
			user.setEmail(staff.getEmail());
		}

		return user;
	}

	@Override
	public List<StaffTO> fetchAllStaff() {
		log.debug("StaffServiceImpl : fetchAllStaff");
		final List<User> users = userDAO.getAllUsers();

		List<StaffTO> staffs = new LinkedList<StaffTO>();
		for (User user : users) {
			StaffTO staff = getPopulatedStaffTO(user);
			staffs.add(staff);
		}

		log.debug("StaffServiceImpl : fetchAllStaff : LIST [" + staffs + "]");

		return staffs;
	}

}
