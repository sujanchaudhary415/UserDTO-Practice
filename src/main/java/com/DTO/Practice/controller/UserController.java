package com.DTO.Practice.controller;


import com.DTO.Practice.payload.UserRequestDTO;
import com.DTO.Practice.payload.UserResponseDTO;
import com.DTO.Practice.service.UserService;


import com.DTO.Practice.wrapper.PagedResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users")
    public ResponseEntity<UserResponseDTO> createUsers(@Valid @RequestBody UserRequestDTO dto)
    {
        UserResponseDTO saved=userService.createUsers(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/api/users")
    public ResponseEntity<PagedResponse<UserResponseDTO>> getAllUsers(@RequestParam(defaultValue = "0") int pageNumber,
                                                                      @RequestParam(defaultValue = "5") int pageSize,
                                                                      @RequestParam(defaultValue = "id") String sortBy,
                                                                      @RequestParam(defaultValue = "asc") String sortDir)
    {
        return ResponseEntity.ok(
                userService.getAllUsers(pageNumber,pageSize,sortBy,sortDir)
        );

    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id)
    {
        UserResponseDTO user=userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);

    }


}
