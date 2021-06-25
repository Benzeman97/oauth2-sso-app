package com.benz.oauth2.sso.api.service;

import com.benz.oauth2.sso.api.entity.OAuth2User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.List;

public interface OAuth2Service {

    OAuth2User saveOAuth2User(OAuth2AuthenticationToken token);
    List<OAuth2User> gatOAuth2Users();
    OAuth2User getOAuth2User(String email);

}
