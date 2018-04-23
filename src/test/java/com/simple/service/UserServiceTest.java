package com.simple.service;


import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.simple.model.ResponseModel;
import com.simple.model.user.UserRequest;
import com.simple.model.user.UserResponse;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Autowired
    private Environment env;

    @Autowired
    private ResourceLoader resourceLoader;


    @Before
    public void before_each_test() throws IOException {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }


    @Test
    public void getUserByIdSuccess()throws Exception{

        String pathPaymentCreditcardReminder = "v1/user";
        String id = "10";
        String userStatus = "I'm OK";

        mockServer.expect(requestTo(Matchers.containsString(pathPaymentCreditcardReminder)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(readJsonFile("success.json"), MediaType.APPLICATION_JSON));

        ResponseModel<UserResponse> result = userService.getUserId(id);

        assertThat(result.getDataObj().getStatus(), equalTo(userStatus));
    }


    @Test(expected = NullPointerException.class)
    public void getUserByIdFail()throws NullPointerException,Exception{

        String pathPaymentCreditcardReminder = "v1/user";
        String id = "10";
        String userStatus = "I'm Not OK";

        mockServer.expect(requestTo(Matchers.containsString(pathPaymentCreditcardReminder)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(readJsonFile("validation-fail.json"), MediaType.APPLICATION_JSON));

        ResponseModel<UserResponse> result = userService.getUserId(id);

        assertThat(result.getDataObj().getStatus(), equalTo(userStatus));
    }


    private String readJsonFile(String fileName) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:testdata/" + fileName);
        return Files.toString(resource.getFile(), Charsets.UTF_8);
    }

}
