package com.pet.cafe.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Schema(description = "SignIn")
@Getter
@Setter
public class LoginUserDTO {
    //TO-DO
    @Schema(description = "Email", example = "ivanov@gmail.com")
    @Size(min = 5, max = 255, message = "Email should contain 5-255 characters")
    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Email must match user@example.com")
    private String email;

    @Schema(description = "Password", example = "my_1secret1_password")
    @Size(max = 30, message = "Password cannot contain more than 30 characters")
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
