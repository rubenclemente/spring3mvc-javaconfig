package org.rcs.controller;

import org.rcs.domain.Role;
import org.rcs.domain.Suser;
import org.rcs.dto.UserDto;
import org.rcs.repository.UserRepository;
import org.rcs.util.RoleUtil;
import org.rcs.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class AccessController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/login")
	public String login() {
		return "access/login";
	}

	@RequestMapping("/denied")
	public String denied(ModelMap model) {
		model.addAttribute("error", "access.denied");
		return "error";
	}

	@RequestMapping("/login/failure")
	public String loginFailure(ModelMap model) {
		model.addAttribute("status", "login.failure");
		return "access/login";
	}

	@RequestMapping("/logout/success")
	public String logoutSuccess(ModelMap model) {
		model.addAttribute("status", "logout.success");
		return "access/login";
	}

	@RequestMapping("/signup")
	public String signup() {
		return "access/signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String createAccount(UserDto dto, ModelMap model) {
		if (this.userRepository.findByUsername(dto.getUsername()) != null) {
			model.addAttribute("status", "signup.invalid.username.duplicate");
			return "access/signup";
		}

		if (dto.getPassword().equals(dto.getRepassword()) == false) {
			model.addAttribute("status", "signup.invalid.password.notmatching");
			return "access/signup";
		}

		Suser user = UserMapper.map(dto);
		user.setRole(new Role(RoleUtil.ROLE_USER, user));
		user = this.userRepository.save(user);

		return "redirect:/";
	}
}