package com.pradeep.menu.test;

import org.hibernate.Session;

import com.pradeep.menu.bean.entity.UserType;
import com.pradeep.menu.util.hibernate.HibernateUtil;

public class TestHibernateCRUD {

	public static void main(String... args) {

		new TestHibernateCRUD().serviceCallToSaveUser();
	}

	
	private static void hibernateCallToSaveUser() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();

		// UserType user = new UserType("STAFF", "Staff only", true, new
		// Date());
		final UserType userType2 = (UserType) session.get(UserType.class, new Long(1));
		/*User user = new User("Pradeep", "Raveendra", "", CommonUtils.getDate("1984/09/20"), "0449 774 702", true, 'M',
				userType2,, "");
		session.saveOrUpdate(user);
		session.flush();
		System.out.println("Saved User [" + user.getId() + "]");*/
		// System.out.println(CommonUtils.getDate("1984/09/20"));
	}

	private  void serviceCallToSaveUser() {
		
    	/*ApplicationContext appContext = 
  	    	  new ClassPathXmlApplicationContext("resources/BeanLocations.xml");

		final StaffTO to = new StaffTO("Prisha", "Pradeep", "", CommonUtils.getDate("2014/08/24"), "0449 774 702", true, 'M',
				"CUSTOMER");
    	StaffService staffService= (StaffService)appContext.getBean("staffService");

		System.out.println(staffService); 
		staffService.save(to);
		System.out.println("SAVED [");
			*/	
	}
}
