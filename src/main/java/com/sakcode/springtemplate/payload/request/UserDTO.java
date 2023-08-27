package com.sakcode.springtemplate.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO {
    @Schema(type = "string", example = "Clark Kent")
    @JsonProperty("username")
//    @NotBlank(message = "username is required.")
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
