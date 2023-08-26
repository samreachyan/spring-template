package com.sakcode.springtemplate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String username;

    @Email
    @NotNull
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
