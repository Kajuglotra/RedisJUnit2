package org.gfg.junitmock.demo.Repository;

import org.gfg.junitmock.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
