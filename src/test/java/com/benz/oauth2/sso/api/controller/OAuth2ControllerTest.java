package com.benz.oauth2.sso.api.controller;

import com.benz.oauth2.sso.api.entity.OAuth2User;
import com.benz.oauth2.sso.api.service.OAuth2Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest
@ExtendWith({SpringExtension.class})
@DisplayName("OAuth2ControllerTest")
public class OAuth2ControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRegistrationRepository clientRegistrationRepository;

    @MockBean
    private OAuth2Service oAuth2Service;

    @Test
    @DisplayName("getOAuth2UsersTest")
    public void getOAuth2UsersTest() throws Exception{

        List<OAuth2User> expectedUsers = new ArrayList<>(Arrays.asList(oAuth2User_1(),oAuth2User_2()));

        when(oAuth2Service.gatOAuth2Users()).thenReturn(expectedUsers);

        MvcResult result = mockMvc.perform(get("/oauth2/users")).
                andExpect(status().isOk())
                .andReturn();

        int actualResponse = result.getResponse().getStatus();
        int expectedResponse = HttpStatus.OK.value();

        Assertions.assertEquals(expectedResponse,actualResponse,
                ()->"get all users with expected status "+expectedResponse+" but actual was "+actualResponse);

    }

    @Test
    @DisplayName("getOAuth2UserTest")
    public void getOAuth2UserTest() throws Exception {

        OAuth2User expectedUser = oAuth2User_1();

        when(oAuth2Service.getOAuth2User(any(String.class))).thenReturn(expectedUser);

        MvcResult result = mockMvc.perform(get("/oauth2/user/{id}",expectedUser.getEmail()))
                .andExpect(status().isOk()).andReturn();

        int actualResponse = result.getResponse().getStatus();
        int expectedResponse = HttpStatus.OK.value();

        Assertions.assertEquals(expectedResponse,actualResponse,
                ()->"find user with expected status "+expectedResponse+" but actual was "+actualResponse);

    }


    private OAuth2User oAuth2User_1(){
        OAuth2User user=new OAuth2User();
        user.setEmail("nafazbenzema@gmail.com");
        user.setName("Nafaz Benzema");
        user.setOauth2Provider("google");
        user.setRoles("ROLE_USER");
        user.setLastLogin(LocalDateTime.now());
        return user;
    }

    private OAuth2User oAuth2User_2(){
        OAuth2User user=new OAuth2User();
        user.setEmail("brook@hotmail.com");
        user.setName("Brook Kelly");
        user.setOauth2Provider("okta");
        user.setRoles("ROLE_USER");
        user.setLastLogin(LocalDateTime.now());
        return user;
    }

}
