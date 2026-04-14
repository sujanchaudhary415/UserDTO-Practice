package com.DTO.Practice.service;

import com.DTO.Practice.payload.UserRequestDTO;
import com.DTO.Practice.payload.UserResponseDTO;



public interface UserService {
    UserResponseDTO createUsers(UserRequestDTO dto);
}
