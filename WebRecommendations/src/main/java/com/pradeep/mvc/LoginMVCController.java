package com.pradeep.mvc;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pradeep.menu.bean.to.user.StaffTO;
import com.pradeep.menu.bean.to.user.UserTO;
import com.pradeep.menu.bo.user.StaffService;
import com.pradeep.menu.bo.user.UserService;
import com.pradeep.menu.dao.user.CommonUtils;

@Controller
@RequestMapping("/")
public class LoginMVCController {
	@Autowired
	StaffService staffService;
	
	@Autowired
	UserService userService;
	
	final Logger log = LogManager.getLogger(LoginMVCController.class); 

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String welcomePage(ModelMap model) {

		log.debug("LoginMVCController : welcomePage : Start  ");
		return "index";
	}

	@RequestMapping(value = { "/springMVCSkillShow" }, method = RequestMethod.GET)
	public String springMVCSkillShow(ModelMap model) {

		log.debug("LoginMVCController : springMVCSkillShow : Start  ");
		return "smvcSkillShow";
	}

	@RequestMapping(value = { "/ajsLogin" }, method = RequestMethod.GET)
	public String angularJSSkillShow(ModelMap model) {

		log.debug("LoginMVCController : angularJSSkillShow : Start  ");
		return "ajsLoginPage";
	}

	@RequestMapping(value = { "/ajsSignIn" }, method = RequestMethod.POST)
	public String ajsSignIn(@RequestParam("userEmail") String userEmailID) {
		log.debug("LoginMVCController : ajsSignIn : Start  ");
		final UserTO user = userService.findUserByEmail(userEmailID);
		
		String redirectPage = "ajsUserPreferencesHome";
		if(user==null){
			//Redirect to registration page
			redirectPage = "ajsRegistrationPage";
		}
		return redirectPage;
	}
	
	@RequestMapping(value = { "/ajsRegisterNew" }, method = RequestMethod.POST)
	public String ajsRegisterNewUser(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("middleName") String middleName,
			@RequestParam("email") String email,
			@RequestParam("dob") String dob,
			@RequestParam("mobile") String mobile,
			@RequestParam("sex") String sex,
			@RequestParam("userType") String userType
			) {
		log.debug("LoginMVCController : ajsRegisterNewUser : Start  ");
		UserTO user = new UserTO(firstName,
				lastName,
				middleName,
				CommonUtils.getDate(dob),
				mobile,
				true,
				sex.toCharArray()[0],
				"CUSTOMER",email);//TODO:Change this
		
		user = userService.save(user);
		
		return "ajsUserPreferencesHome";
	}
	
	
	
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		log.debug("LoginMVCController : listUsers : Start  ");
		final List<StaffTO> users = staffService.fetchAllStaff();

		log.debug("LoginMVCController : listUsers : LIST Retirned [" + users + "]");

		model.addAttribute("staffs", users);
		return "staffsList";
	}

}
