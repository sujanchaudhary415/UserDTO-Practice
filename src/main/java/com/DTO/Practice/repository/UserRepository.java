package com.DTO.Practice.repository;

import com.DTO.Practice.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Long> {
   boolean existsByEmail(String email);
}
