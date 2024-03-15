package com.example.validatingforminput.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {
    public User getUserByUsername(@Param("userNameOrEmail") String userNameOrEmail);
    public User getUserByUserEmail(@Param("userNameOrEmail") String userNameOrEmail);
}
