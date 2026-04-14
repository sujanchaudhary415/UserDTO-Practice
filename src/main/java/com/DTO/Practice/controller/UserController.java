package com.DTO.Practice.controller;


import com.DTO.Practice.payload.UserRequestDTO;
import com.DTO.Practice.payload.UserResponseDTO;
import com.DTO.Practice.service.UserService;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/addusers")
    public ResponseEntity<UserResponseDTO> createUsers(@Valid @RequestBody UserRequestDTO dto)
    {
        UserResponseDTO saved=userService.createUsers(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
