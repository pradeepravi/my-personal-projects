package com.pradeep.menu.dao.user.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.pradeep.menu.bean.entity.UserType;
import com.pradeep.menu.dao.user.UserTypeDAO;
import com.pradeep.menu.util.hibernate.HibernateUtil;

@Component("userTypeDAOImpl")
public class UserTypeDAOImpl implements UserTypeDAO {

	@Override
	public boolean save(UserType user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(UserType user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(UserType user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserType> getUserTypes(List<UserType> users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserType getUserType(String userType) {
		final String sql = "from UserType where type=:userType";
		Session session = HibernateUtil.getSession();
		UserType userTypeObj = (UserType) session.createQuery(sql).
				setParameter("userType", userType).uniqueResult();
		return userTypeObj;

	}

}
