package com.pradeep.menu.bo.user;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pradeep.menu.bean.to.user.UserTO;



@Component("userService")
public interface UserService {
	public UserTO findUserByID(long staffID);
	public UserTO findUserByEmail(String email);
	public List<UserTO> fetchAllUser();
	public UserTO save(UserTO user);
	public UserTO update(UserTO user);
	public boolean delete(UserTO user);

}
