package com.socman.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.socman.data.orm.Property;
import com.socman.data.service.SocietyDataService;
import com.socman.view.dto.PropertyView;
import com.socman.view.dto.SocietyView;

@Controller
@RequestMapping(value = "/")
public class SocietyController {

	@Autowired
	SocietyDataService societyDataService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:/public/home";

	}

	@RequestMapping(value = "/public/home", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView home() {
		return new ModelAndView("society");

	}

	@RequestMapping(value = "/public/aboutus", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView aboutus() {
		return new ModelAndView("aboutus");
	}

	@RequestMapping(value = "/public/newsevents", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView news() {
		return new ModelAndView("newsevents");
	}

	@RequestMapping(value = "/public/team", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView team() {
		return new ModelAndView("team");
	}

	@RequestMapping(value = "/public/contactus", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView contactus() {
		return new ModelAndView("contactus");
	}

	@RequestMapping(value = "/public/login", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/public/register", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView registration() {
		return new ModelAndView("registration");
	}

	@RequestMapping(value = "/details", method = { RequestMethod.GET })
	public ModelAndView getSocDetails() {
		return new ModelAndView("admin.soc.info");
	}

	@RequestMapping(value = "/details/{socId}", method = { RequestMethod.GET }, produces = "application/json")
	public ResponseEntity<?> getSocDetails(@PathVariable("socId") int socId) {
		SocietyView view = null;
		try {

			view = societyDataService.getSocietyData(socId);

		} catch (AccessDeniedException e) {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<SocietyView>(view, HttpStatus.OK);
	}

	@RequestMapping(value = "/property", method = { RequestMethod.GET }, produces = "application/json")
	public ResponseEntity<?> getProperties() {
		List<PropertyView> view = new ArrayList<PropertyView>();
		try {
			List<Property> properties = societyDataService.getProperties();
			for (Property property : properties) {
				PropertyView propertyView = new PropertyView();
				propertyView.setPropertyID(property.getPropertyID());
				propertyView.setPropertyName(property.getPropertyName());
				propertyView.setPropertyType(property.getPropertyType());
				view.add(propertyView);
			}

		} catch (AccessDeniedException e) {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<List<?>>(view, HttpStatus.OK);
	}
}
