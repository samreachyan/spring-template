package com.sakcode.springtemplate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotNull
    @Min(5)
    private String password;

    @NotNull
    private Integer gender;

    @NotNull
    @Pattern(regexp = "^\\d{10}$")
    private String phone;

}
