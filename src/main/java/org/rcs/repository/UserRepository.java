package org.rcs.repository;

import org.rcs.domain.Suser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Suser, Long> {

	Suser findByUsername(String username);
}
