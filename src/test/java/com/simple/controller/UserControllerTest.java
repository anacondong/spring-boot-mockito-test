package com.simple.controller;


import com.simple.model.ResponseModel;
import com.simple.model.user.UserResponse;
import com.simple.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserController ctaController;

    @MockBean
    UserService userService;

    @Test
    public void getUserByIdSuccess() throws Exception {
        // let assign
        String id = "10";
        String userStatus = "I'm OK";
        UserResponse userResponse = new UserResponse();
        userResponse.setStatus(userStatus);

        // when Mock Object
        // it's request and response should be the same input and output in real method
        when(userService.getUserId(anyString()))
                .thenReturn(new ResponseModel<UserResponse>(00001).setDataObj(userResponse));

        // Do
        // calling real method but not call in case Mock method "when" setup
        ResponseEntity<ResponseModel<UserResponse>> response = ctaController.getUser(id);

        // expected
        assertThat(response.getBody().getCode(), equalTo(00001));
        assertThat(response.getBody().getDataObj().getStatus(), equalTo(userStatus));
    }
    @Test
    public void getUserByIdFail() throws Exception {
        // let assign
        String id = "10";
        String userStatus = "I'm Not OK";
        UserResponse userResponse = new UserResponse();
        userResponse.setStatus(userStatus);

        // when Mock Object
        // it's request and response should be the same input and output in real method
        when(userService.getUserId(anyString()))
                .thenReturn(new ResponseModel<UserResponse>(99999).setDataObj(userResponse));

        // Do
        // calling real method but not call in case Mock method "when" setup
        ResponseEntity<ResponseModel<UserResponse>> response = ctaController.getUser(id);

        // expected
        assertThat(response.getBody().getCode(), equalTo(99999));
        assertThat(response.getBody().getDataObj().getStatus(), equalTo(userStatus));
    }


}
