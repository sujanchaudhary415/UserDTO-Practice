package com.DTO.Practice.service;

import com.DTO.Practice.payload.UserRequestDTO;
import com.DTO.Practice.payload.UserResponseDTO;
import com.DTO.Practice.wrapper.PagedResponse;



public interface UserService {
    UserResponseDTO createUsers(UserRequestDTO dto);
    PagedResponse<UserResponseDTO> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);
    UserResponseDTO getUserById(Long id);
}
