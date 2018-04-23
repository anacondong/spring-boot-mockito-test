package com.simple.controller;


import com.simple.model.ResponseModel;
import com.simple.model.user.UserRequest;
import com.simple.model.user.UserResponse;
import com.simple.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ittipol.jannapast on 04/19/2018.
 */

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(value = {"/v1/user"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel<UserResponse>> getUser(String id) throws Exception{
        ResponseModel<UserResponse> dataOutput = userService.getUserId(id);
        return new ResponseEntity<ResponseModel<UserResponse>>(dataOutput, null, HttpStatus.OK);
    }

    @PostMapping(value = {"/v1/user/restTemplate"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel<UserResponse>> getUserRestTemplate(@RequestBody UserRequest userRequest) throws Exception{
        ResponseModel<UserResponse> dataOutput = userService.getUserIdRestTemplate(userRequest);
        return new ResponseEntity<ResponseModel<UserResponse>>(dataOutput, null, HttpStatus.OK);
    }

}
