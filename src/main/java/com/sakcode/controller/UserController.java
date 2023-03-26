package com.sakcode.controller;

import com.sakcode.dto.request.UserRequestDTO;
import com.sakcode.entity.User;
import com.sakcode.mapper.UserMapper;
import com.sakcode.service.UserService;
import com.sakcode.dto.response.ResponseBase;
import com.sakcode.exception.UserNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private UserMapper userMapper;


    @GetMapping
    public ResponseEntity<?> getAllUserList() {
        log.info("Begin UserController: getAllUserList");
        List<User> userList = userService.findAllUser();

        log.info("Done UserController: getAllUserList");
        return ResponseEntity.ok(userList);
    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        log.info("Begin method createNewUser");
        User newUser = userMapper.userDTOtoEntity(userRequestDTO);
        newUser = userService.save(newUser);

        log.info("Done method createNewUser");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseBase.builder()
                        .status(HttpStatus.CREATED.value())
                        .message("User created.")
                        .data(newUser)
                .build()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findUserById(id));
    }
}
