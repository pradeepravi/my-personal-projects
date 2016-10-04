package com.pradeep.menu.dao.user.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.menu.bean.entity.User;
import com.pradeep.menu.dao.user.UserDAO;
import com.pradeep.menu.util.hibernate.HibernateUtil;

@Component("userDAOImpl")
public class UserDAOImpl implements UserDAO {

	final Logger log = LogManager.getLogger(UserDAOImpl.class);

	@Override
	@Transactional
	public User save(User user) {

		Session session = null;
		Transaction transaction = null;
		try {
			log.debug("UserDAOImpl : save : About to save");
			session = HibernateUtil.getSession();
			session.setFlushMode(FlushMode.COMMIT);
			transaction = session.beginTransaction();
			session.save(user);
			// session.flush();
			log.debug("UserDAOImpl : save : Saved [" + user.getId() + "]");
		} catch (HibernateException e) {
			log.error("UserDAOImpl : save : Hibernate Exception", e);
			transaction.rollback();
			e.printStackTrace();

		} catch (Exception e) {
			transaction.rollback();
			log.error("UserDAOImpl : save : Exception", e);
			e.printStackTrace();
		} finally {
			transaction.commit();
			session.close();
			log.debug("UserDAOImpl : save : IS SUCCESS [" + true + "]");
		}
		return user;
	}

	@Override
	public User update(User user) {
		log.debug("UserDAOImpl : update : start");
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.update(user);
			session.flush();
			log.debug("UserDAOImpl : update :  Update [" + user.getId() + "]");
		} catch (HibernateException e) {
			log.error("UserDAOImpl : update : Hibernate Exception", e);
			e.printStackTrace();
		} catch (Exception e) {

			log.error("UserDAOImpl : update: Exception", e);
			e.printStackTrace();
		} finally {
			session.close();
			log.debug("UserDAOImpl : update : IS SUCCESS [" + true + "]");
		}
		return user;
	}

	@Override
	public boolean delete(User user) {
		log.debug("UserDAOImpl : delete : start");

		user.setActive(false);
		user = update(user);

		log.debug("UserDAOImpl : delete ");

		return true;
	}

	@Override
	public List<User> getUsers(List<User> users) {
		log.debug("UserDAOImpl : getUsers : start");
		List<User> usersList = new LinkedList<User>();

		for (User user : users) {
			usersList.add(getUser(user));
		}

		log.debug("UserDAOImpl : USERS [" + usersList.size() + "]");
		return usersList;
	}

	@Override
	public List<User> getAllUsers() {
		log.debug("UserDAOImpl : getAllUsers: start");

		final String sql = "from User";
		Session session = HibernateUtil.getSession();
		final List<User> users = (List<User>) session.createQuery(sql).list();

		log.debug("UserDAOImpl : getAllUsers : Users [" + users + "]");

		return users;

	}

	@Override
	public User getUser(User user) {
		log.debug("UserDAOImpl : getUser : Start[" + user + "]");
		User populatedUser = null;
		if (user != null) {
			Session session = null;
			try {

				session = HibernateUtil.getSession();

				if (user.getId() > 0) {
					populatedUser = (User) session.get(User.class, user.getId());
					if (populatedUser != null) {
						log.debug("Found User [" + populatedUser.getFirstName() + "]");
					}
				} else {
					log.debug("UserDAOImpl : getUser : Found User NO ID Found so doing a Croteria Query");

					final Criteria criteria = session.createCriteria(User.class);
					if (user.getEmail() != null) {
						log.debug("UserDAOImpl : getUser : Email");
						criteria.add(Restrictions.eq("email", user.getEmail()));
					}

					if (user.getFirstName() != null) {
						criteria.add(Restrictions.eq("first_name", user.getFirstName()));
					}

					if (user.getLastName() != null) {
						criteria.add(Restrictions.eq("last_name", user.getLastName()));
					}

					if (user.getMiddleName() != null) {
						criteria.add(Restrictions.eq("middle_name", user.getMiddleName()));
					}

					if (user.getMobileNumber() != null) {
						criteria.add(Restrictions.eq("mobile_number", user.getMobileNumber()));
					}

					populatedUser = (User) criteria.uniqueResult();
					log.debug("UserDAOImpl : getUser : Found User [" + populatedUser.getFirstName() + "]");
				}
			} catch (HibernateException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		log.debug("UserDAOImpl : getUser : End");
		return populatedUser;
	}

}
