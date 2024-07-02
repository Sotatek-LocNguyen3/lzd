package com.example.lazadu.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {

    /*
        Only contain lower case and number and dot.
     */
    @NotBlank
    @Pattern(
            regexp = "^[a-z0-9.]+$",
            message = "Not a valid username"
    )
    private String username;

    @Email(message = "Not a valid email")
    private String email;

    /*
        + Length greater than 8 and less than 20
        + Contain at lest 1 special characters
        + Contain at least 1 uppercase
        + Contain at least 1 lowercase
        + Contain at least 1 number
     */
    @NotBlank
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Not a valid password"
    )
    private String password;

    private String phoneNo;
}
