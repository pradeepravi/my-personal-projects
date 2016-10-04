package com.pradeep.ws;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.menu.bean.to.user.StaffTO;
import com.pradeep.menu.bo.user.StaffService;

@RestController
public class StaffServiceWSController {

	@Autowired
	StaffService staffService; // Service which will do all data
								// retrieval/manipulation work
	final Logger log = LogManager.getLogger(StaffServiceWSController.class );

	@RequestMapping(value = "/staff", method = RequestMethod.GET)
	public ResponseEntity<List<StaffTO>> listAllStaff() {
		List<StaffTO> users = staffService.fetchAllStaff();
		if (users.isEmpty()) {
			return new ResponseEntity<List<StaffTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<StaffTO>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/staff/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<StaffTO> findStaffByID(@PathVariable("id") long id) {
		log.debug("StaffServiceController : findStaffByID : start");
		StaffTO staff = staffService.findStaffByID(id);
		if (staff != null) {
			log.debug("StaffServiceController : findStaffByID : No Staff found for ID [" + id + "]");
			return new ResponseEntity<StaffTO>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		log.debug("StaffServiceController : findStaffByID : Staff Found returning Staff [" + staff + "]");
		return new ResponseEntity<StaffTO>(staff, HttpStatus.OK);
	}

	@RequestMapping(value = "/staff/save/", method = RequestMethod.POST)
	public ResponseEntity<StaffTO> saveStaff(@RequestBody StaffTO staff) {
		log.debug("StaffServiceController : saveStaff : Start ");

		if (staff != null) {

			log.debug("StaffServiceController : saveStaff : About to save");
			staffService.save(staff);
		} else {
			log.debug("StaffServiceController : saveStaff : Staff object was Null");

			return new ResponseEntity<StaffTO>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<StaffTO>(staff, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/staff/update/{id}", method = RequestMethod.POST)
	public ResponseEntity<StaffTO> updateStaff(@PathVariable("id") long id, @RequestBody StaffTO staff) {
		log.debug("StaffServiceController : updateStaff : Start ");

		if (staff == null) {
			log.debug("StaffServiceController : updateStaff : No Staff for ID [" + id + "]");
			return new ResponseEntity<StaffTO>(HttpStatus.NOT_FOUND);
		} else {
			log.debug("StaffServiceController : updateStaff : About to update");

			staffService.update(staff);
		}
		return new ResponseEntity<StaffTO>(staff, HttpStatus.OK);
	}

	@RequestMapping(value = "/staff/delete/{id}", method = RequestMethod.POST)
	public ResponseEntity<StaffTO> deleteStaff(@PathVariable("id") long id, @RequestBody StaffTO staff) {
		log.debug("StaffServiceController : deleteStaff : Start ");

		if (staff == null) {
			log.debug("StaffServiceController : deleteStaff : No Staff for ID [" + id + "]");
			return new ResponseEntity<StaffTO>(HttpStatus.NO_CONTENT);
		} else {
			log.debug("StaffServiceController : deleteStaff : About to delete (more like update Active to false)");
			staff.setActive(false);
			staffService.update(staff);
		}
		log.debug("StaffServiceController : deleteStaff : Return after saving");

		return new ResponseEntity<StaffTO>(staff, HttpStatus.OK);
	}

}