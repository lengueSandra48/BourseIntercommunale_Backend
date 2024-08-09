package com.pk48.BourseIntercommunale.domain.repository;

import com.pk48.BourseIntercommunale.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);
}
