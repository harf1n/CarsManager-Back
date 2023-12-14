package com.uedge.kursach.repository;

import com.uedge.kursach.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String name);
}
