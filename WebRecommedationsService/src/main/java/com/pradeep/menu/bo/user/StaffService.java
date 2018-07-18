package com.pradeep.menu.bo.user;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pradeep.menu.bean.to.user.StaffTO;

@Component("staffService")
public interface StaffService {
	public StaffTO findStaffByID(long staffID);
	public List<StaffTO> fetchAllStaff();
	public boolean save(StaffTO user);
	public boolean update(StaffTO user);
	public boolean delete(StaffTO user);

}
