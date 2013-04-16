package org.rcs.util;

import java.util.ArrayList;
import java.util.List;

import org.rcs.domain.Suser;
import org.rcs.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class UserMapper {

	public static UserDto map(Suser user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setUsername(user.getUsername());
		dto.setRole(user.getRole().getRole());

		return dto;
	}

	public static Suser map(UserDto dto) {
		Suser user = new Suser();
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setUsername(dto.getUsername());

		PasswordEncoder encoder = new Md5PasswordEncoder();
		user.setPassword(encoder.encodePassword(dto.getPassword(), null));

		return user;
	}

	public static List<UserDto> map(Page<Suser> users) {
		List<UserDto> dtos = new ArrayList<UserDto>();
		for (Suser user : users) {
			dtos.add(map(user));
		}

		return dtos;
	}
}
