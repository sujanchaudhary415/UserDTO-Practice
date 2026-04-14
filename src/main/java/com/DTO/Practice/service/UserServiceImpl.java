package com.DTO.Practice.service;

import com.DTO.Practice.exceptions.UserAlreadyExistException;
import com.DTO.Practice.model.UserModel;
import com.DTO.Practice.payload.UserRequestDTO;
import com.DTO.Practice.payload.UserResponseDTO;
import com.DTO.Practice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponseDTO createUsers(UserRequestDTO dto) {
        if(userRepo.existsByEmail(dto.getEmail()))
            throw new UserAlreadyExistException("User already Exist with email: ");

        UserModel user=modelMapper.map(dto, UserModel.class);
        UserModel saved=userRepo.save(user);
        return modelMapper.map(saved,UserResponseDTO.class);
    }
}
