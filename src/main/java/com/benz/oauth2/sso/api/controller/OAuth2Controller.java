package com.benz.oauth2.sso.api.controller;

import com.benz.oauth2.sso.api.entity.OAuth2User;
import com.benz.oauth2.sso.api.service.OAuth2Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/oauth2")
@CrossOrigin(origins = "*",maxAge = 3600)
public class OAuth2Controller {

    @Value("${front.end.url}")
    private String redirect_url;

    private OAuth2Service oAuth2Service;

    public OAuth2Controller(OAuth2Service oAuth2Service){
        this.oAuth2Service=oAuth2Service;
    }


    @GetMapping("/user")
    public ResponseEntity<Void> loginWithOauth2(OAuth2AuthenticationToken token,HttpServletResponse response) throws IOException{

        oAuth2Service.saveOAuth2User(token);

       response.sendRedirect(redirect_url);

        return new ResponseEntity<>(HttpStatus.PERMANENT_REDIRECT);

    }

    @GetMapping("/users")
    public ResponseEntity<List<OAuth2User>> getOAuth2Users(){
        return ResponseEntity.ok().body(oAuth2Service.gatOAuth2Users());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<OAuth2User> getOAuth2User(@PathVariable("id") String email){
        if(email.trim().isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(oAuth2Service.getOAuth2User(email),HttpStatus.OK);
    }



}
