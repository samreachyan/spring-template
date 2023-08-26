package com.sakcode.springtemplate.controller;

import com.sakcode.springtemplate.domain.User;
import com.sakcode.springtemplate.exception.UserNotFoundException;
import com.sakcode.springtemplate.payload.request.UserDTO;
import com.sakcode.springtemplate.payload.response.BaseResponse;
import com.sakcode.springtemplate.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<?> getAllUserList() {
        log.info("Begin UserController: getAllUserList");
        List<User> userList = userService.findAllUser();

        log.info("Done UserController: getAllUserList");
        return ResponseEntity.ok(userList);
    }

    @PostMapping()
    public ResponseEntity<?> createNewUser(@RequestBody UserDTO userDTO) {
        log.info("Begin method createNewUser");
        User newUser = this.modelMapper.map(userDTO, User.class);
        newUser = userService.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        BaseResponse.builder()
                                .status(HttpStatus.CREATED.value())
                                .message("User created.")
                                .data(newUser)
                                .build()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findUserById(id).orElseThrow(() -> new UserNotFoundException("User is not found = " + id)));
    }

}
