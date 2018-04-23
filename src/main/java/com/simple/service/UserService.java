package com.simple.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.model.ResponseModel;
import com.simple.model.user.User;
import com.simple.model.user.UserRequest;
import com.simple.model.user.UserResponse;
import com.simple.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    public ResponseModel<UserResponse> getUserId(String id)throws  Exception{
        UserRequest userRequest = new UserRequest();
        userRequest.setId(id);
        userRequest.setUsername("I'm OK");

        ResponseModel<UserResponse> userResponse= this.getUserIdRestTemplate(userRequest);
        return new ResponseModel<UserResponse>(00001)
                .setDataObj(userResponse.getDataObj());
    }

    public ResponseModel<UserResponse> getUserIdRestTemplate(UserRequest userRequest)throws  Exception{

        // input entity
        ObjectMapper request = new ObjectMapper();
        HttpEntity<UserRequest> entity = new HttpEntity<UserRequest>(userRequest, null);

        // response Type
        ParameterizedTypeReference<ResponseModel<UserResponse>> responseType = new ParameterizedTypeReference<ResponseModel<UserResponse>>() {};

        // simple call itself for simple Using RestTemplate
        ResponseEntity<ResponseModel<UserResponse>> userResponse= restTemplate.exchange("http://localhost:8080/v1/user", HttpMethod.POST, entity, responseType);

        return new ResponseModel<UserResponse>(00001)
                .setDataObj(userResponse.getBody().getDataObj());
    }

}
