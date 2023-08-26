package com.sakcode.springtemplate.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO {
    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("gender")
    private Integer gender;

    @JsonProperty("phone")
    private String phone;
}
