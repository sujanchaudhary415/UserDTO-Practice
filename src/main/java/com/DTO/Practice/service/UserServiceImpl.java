package com.DTO.Practice.service;

import com.DTO.Practice.exceptions.APIExceptions;
import com.DTO.Practice.exceptions.ResourceNotFoundException;
import com.DTO.Practice.exceptions.UserAlreadyExistException;
import com.DTO.Practice.model.UserModel;
import com.DTO.Practice.payload.UserRequestDTO;
import com.DTO.Practice.payload.UserResponseDTO;
import com.DTO.Practice.repository.UserRepository;
import com.DTO.Practice.utility.PaginationUtil;
import com.DTO.Practice.wrapper.PagedResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private  UserRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponseDTO createUsers(UserRequestDTO dto) {
        if(userRepo.existsByEmail(dto.getEmail()))
            throw new UserAlreadyExistException("User already Exist with email: " +dto.getEmail());

        UserModel user=modelMapper.map(dto, UserModel.class);
        UserModel saved=userRepo.save(user);
        return modelMapper.map(saved,UserResponseDTO.class);
    }

    @Override
    public PagedResponse<UserResponseDTO> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<UserModel> page= userRepo.findAll(pageable);


        // ❌ DO NOT throw exception for empty page

        List<UserResponseDTO> dtoList = page.getContent()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .toList();

        return PaginationUtil.build(page, dtoList);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        UserModel user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","UserId",id));
        return modelMapper.map(user,UserResponseDTO.class);
    }
}
