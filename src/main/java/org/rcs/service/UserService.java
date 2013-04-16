package org.rcs.service;

import org.rcs.domain.Suser;
import org.rcs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repository;

	public Boolean create(Suser user) {
		Suser existingUser = this.repository.findByUsername(user.getUsername());
		if (existingUser != null) {
			return false;
		}

		user.getRole().setSuser(user);
		Suser saved = this.repository.save(user);
		if (saved == null) {
			return false;
		}

		return true;
	}

	public Boolean update(Suser user) {
		Suser existingUser = this.repository.findByUsername(user.getUsername());
		if (existingUser == null) {
			return false;
		}

		// Only firstName, lastName, and role fields are updatable
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.getRole().setRole(user.getRole().getRole());

		Suser saved = this.repository.save(existingUser);
		if (saved == null) {
			return false;
		}

		return true;
	}

	public Boolean delete(Suser user) {
		Suser existingUser = this.repository.findOne(user.getId());
		if (existingUser == null) {
			return false;
		}

		this.repository.delete(existingUser);
		Suser deletedUser = this.repository.findOne(user.getId());
		if (deletedUser != null) {
			return false;
		}

		return true;
	}
}
