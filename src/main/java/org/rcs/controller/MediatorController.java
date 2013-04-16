package org.rcs.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MediatorController {

	@RequestMapping(value = "/")
	public String getHomePage(ModelMap model) {
		model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication().getName());
		// Redirect in order to the navigation highlight works properly
		return "redirect:/home";
	}

	@RequestMapping(value = "/home")
	public String getHomePage2(ModelMap model) {
		model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication().getName());
		return "home";
	}
}