package org.gfg.junitmock.demo.Repository;

import org.gfg.junitmock.demo.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, Integer> {
    public MyUser findByUsername(String username);
}
