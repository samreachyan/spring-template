package com.sakcode.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private int id;
    private String username;
    private String password;
    private String email;
    private int gender;
}
