package com.sakcode.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
