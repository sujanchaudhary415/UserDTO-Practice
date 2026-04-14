package com.DTO.Practice.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserRequestDTO {
    @NotBlank(message = "Name Can not be empty")
    private String userName;

    @Email(message = "Email format invalid")
    @NotBlank(message="Email can not be left empty")
    private String email;

    @Size(min=6,message = "password must be atleast 6 character")
    private String password;

}
