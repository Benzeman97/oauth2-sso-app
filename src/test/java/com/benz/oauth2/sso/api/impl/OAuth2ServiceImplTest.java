package com.benz.oauth2.sso.api.impl;

import com.benz.oauth2.sso.api.dao.OAuth2UserDao;
import com.benz.oauth2.sso.api.entity.OAuth2User;
import com.benz.oauth2.sso.api.service.OAuth2Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith({SpringExtension.class})
@DisplayName("OAuth2ServiceImplTest")
public class OAuth2ServiceImplTest {

     @Autowired
     private OAuth2Service oAuth2Service;

     @MockBean
     private OAuth2UserDao oAuth2UserDao;

     @Test
     @DisplayName("getOAuth2UsersTest")
     public void getOAuth2UsersTest(){

          List<OAuth2User> expectedUsers = new ArrayList<>(Arrays.asList(oAuth2User_1(),oAuth2User_1()));
          expectedUsers.sort((u1,u2)->u1.getOauth2Provider().compareTo(u2.getOauth2Provider()));

          when(oAuth2UserDao.findAll(Sort.by("oauth2Provider"))).thenReturn(expectedUsers);

          List<OAuth2User> actualUsers = oAuth2Service.gatOAuth2Users();

          Assertions.assertEquals(expectedUsers,actualUsers,"retrieve all users from DB");

     }

     @Test
     @DisplayName("getOAuth2UserTest")
    public void getOAuth2UserTest(){
         OAuth2User expectedUser = oAuth2User_1();

         when(oAuth2UserDao.findById(expectedUser.getEmail())).thenReturn(Optional.of(expectedUser));

         OAuth2User actualUser = oAuth2Service.getOAuth2User(expectedUser.getEmail());

         Assertions.assertEquals(expectedUser,actualUser,"find user by email");
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
