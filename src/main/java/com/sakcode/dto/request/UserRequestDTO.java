package com.sakcode.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequestDTO {
    @NotNull(message = "Username must be not null.")
    private String username;

    @Email(message = "Email must be valid.")
    @NotNull(message = "Email must be be not null.")
    private String email;
    @NotNull(message = "Password must be not mull.")
    @Min(5)
    private String password;

    @NotNull
    private Integer gender;

    @NotNull(message = "Phone must be not null.")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits number.")
    private String phone;

}
