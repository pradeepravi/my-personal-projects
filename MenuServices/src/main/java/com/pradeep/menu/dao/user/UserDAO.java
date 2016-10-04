package com.pradeep.menu.dao.user;

import java.util.List;

import com.pradeep.menu.bean.entity.User;

public interface UserDAO {
	public User save(User user);

	public User update(User user);

	public boolean delete(User user);

	public List<User> getUsers(List<User> users);
	
	public List<User> getAllUsers();

	public User getUser(User users);

}
