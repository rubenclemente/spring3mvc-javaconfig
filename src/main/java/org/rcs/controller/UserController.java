package org.rcs.controller;

import java.util.List;

import org.rcs.domain.Role;
import org.rcs.domain.Suser;
import org.rcs.dto.UserDto;
import org.rcs.repository.UserRepository;
import org.rcs.service.UserService;
import org.rcs.util.RoleUtil;
import org.rcs.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserService service;

	@ModelAttribute("allRoles")
	public List<Integer> getAllRoles() {
		return RoleUtil.roles();
	}

	@ModelAttribute("authname")
	public String getAuthname() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@RequestMapping
	public String getUsersPage(ModelMap model) {
		Pageable pageRequest = new PageRequest(0, 100);
		Page<Suser> users = this.repository.findAll(pageRequest);
		model.addAttribute("users", UserMapper.map(users));
		model.addAttribute("commanduser", new UserDto());
		model.addAttribute("usertype", "new");

		return "users";
	}

	@RequestMapping(value = "/get", produces = "application/json")
	public @ResponseBody
	UserDto get(@RequestBody UserDto user) {
		return UserMapper.map(this.repository.findByUsername(user.getUsername()));
	}

	@RequestMapping(value = "/create", produces = "application/json", method = RequestMethod.POST)
	public String create(UserDto dto) {
		if (dto.getId() != null) {
			Suser existingUser = UserMapper.map(dto);
			existingUser.setRole(new Role(dto.getRole(), existingUser));
			this.service.update(existingUser);
		}
		else {
			Suser newUser = UserMapper.map(dto);
			newUser.setRole(new Role(dto.getRole(), newUser));
			this.service.create(newUser);
		}

		return "redirect:/users";
	}

	@RequestMapping(value = "/edit")
	public String edit(Long id, ModelMap model) {
		Pageable pageRequest = new PageRequest(0, 100);
		Page<Suser> users = this.repository.findAll(pageRequest);
		model.addAttribute("users", UserMapper.map(users));
		model.addAttribute("commanduser", UserMapper.map(this.repository.findOne(id)));
		model.addAttribute("usertype", "update");

		return "users";
	}

	@RequestMapping(value = "/delete")
	public String delete(Long id) {
		Suser existingUser = new Suser();
		existingUser.setId(id);
		this.service.delete(existingUser);

		return "redirect:/users";
	}
}
