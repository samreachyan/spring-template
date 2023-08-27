package com.sakcode.springtemplate.controller;

import com.sakcode.springtemplate.domain.User;
import com.sakcode.springtemplate.exception.UserNotFoundException;
import com.sakcode.springtemplate.payload.request.UserDTO;
import com.sakcode.springtemplate.payload.response.BaseResponse;
import com.sakcode.springtemplate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Get all user information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))})})
    @GetMapping()
    public ResponseEntity<BaseResponse> getAllUserList() {
        log.info("Begin UserController: getAllUserList");
        List<User> userList = userService.findAllUser();

        log.info("Done UserController: getAllUserList");
        Map<String, Object> res = new HashMap<>();
        res.put("users", userList);

        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Success")
                        .data(res)
                        .build());
    }

    @Operation(summary = "Create user information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))})})
    @PostMapping()
    public ResponseEntity<?> createNewUser(@Valid @RequestBody UserDTO userDTO) {
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

    @Operation(summary = "Get User info by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))})})
    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(BaseResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Success")
                .data(userService.findUserById(id).orElseThrow(() -> new UserNotFoundException("User is not found = " + id)))
                .build());
    }

}
