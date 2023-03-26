package com.sakcode.mapper;

import com.sakcode.dto.request.UserRequestDTO;
import com.sakcode.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public UserRequestDTO userToDTO(User user) {
        UserRequestDTO userRequestDTO = modelMapper.map(user, UserRequestDTO.class);
        return userRequestDTO;
    }

    public User userDTOtoEntity(UserRequestDTO userRequestDTO) {
        User userEntity = modelMapper.map(userRequestDTO, User.class);
        return userEntity;
    }

}
