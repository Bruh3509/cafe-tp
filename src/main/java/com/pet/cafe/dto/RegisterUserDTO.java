package com.pet.cafe.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Schema(description = "SignUp")
@Getter
@Setter
public class RegisterUserDTO {

    @Schema(description = "First Name", example = "Ivan")
    @Size(max = 50, message = "First Name cannot contain more than 50 characters!")
    @NotBlank(message = "First Name cannot be empty!")
    private String firstName;

    @Schema(description = "Last Name", example = "Ivanov")
    @Size(max = 50, message = "Last Name cannot contain more than 50 characters!")
    @NotBlank(message = "Last Name cannot be empty!")
    private String lastName;

    @Schema(description = "Second Name", example = "GGG")
    @NotBlank(message = "secondName cannot be empty")
    private String secondName;

    @Schema(description = "phone_number", example = "+375290000000")
    @NotBlank(message = " Phone cannot be empty")
    private String phone_number;


    @Schema(description = "Email", example = "ivanov@gmail.com")
    @Size(min = 5, max = 255, message = "Email should contain 5-255 characters")
    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Email must match user@example.com")
    private String email;

    @Schema(description = "Password", example = "my_1secret1_password")
    @Size(max = 30, message = "Password cannot contain more than 30 characters")
    private String password;


}
