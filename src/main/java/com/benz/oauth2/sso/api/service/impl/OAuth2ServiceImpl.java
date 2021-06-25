package com.benz.oauth2.sso.api.service.impl;

import com.benz.oauth2.sso.api.dao.OAuth2UserDao;
import com.benz.oauth2.sso.api.entity.OAuth2User;
import com.benz.oauth2.sso.api.exception.CustomNullPointerException;
import com.benz.oauth2.sso.api.exception.DataNotFoundException;
import com.benz.oauth2.sso.api.exception.UserExistedException;
import com.benz.oauth2.sso.api.service.OAuth2Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.DataFormatException;

@Service
public class OAuth2ServiceImpl implements OAuth2Service {

    private static Logger LOGGER = LogManager.getLogger(OAuth2ServiceImpl.class);

    private OAuth2UserDao oAuth2UserDao;

    public OAuth2ServiceImpl(OAuth2UserDao oAuth2UserDao){
        this.oAuth2UserDao=oAuth2UserDao;
    }

    @Override
    public OAuth2User saveOAuth2User(OAuth2AuthenticationToken token) {

        List<GrantedAuthority> authorities = new ArrayList<>(token.getAuthorities());
        Map<String,Object> attributes = token.getPrincipal().getAttributes();


        String role = String.valueOf(authorities.get(0));
        String name = String.valueOf(attributes.get("name"));
        String email=  String.valueOf(attributes.get("email"));
        String provider = token.getAuthorizedClientRegistrationId();


        if(Objects.isNull(email) || email.equalsIgnoreCase("null"))
        {
            LOGGER.error("email is null");
            throw new CustomNullPointerException(String.format("email is required, please enable " +
                    "your email for public access on %s",provider));
        }

        OAuth2User user=new OAuth2User();
        user.setEmail(email);
        user.setName(name);
        user.setOauth2Provider(provider);
        user.setRoles(role);
        user.setLastLogin(LocalDateTime.now());

         user = oAuth2UserDao.save(user);
        LOGGER.info(String.format("user has been saved with %s",user.getEmail()));
        return user;
    }

    @Override
    public List<OAuth2User> gatOAuth2Users() {
        List<OAuth2User> users = oAuth2UserDao.findAll(Sort.by("oauth2Provider"));
        return users;
    }

    @Override
    public OAuth2User getOAuth2User(String email) {

        OAuth2User user = oAuth2UserDao.findById(email)
                .orElseThrow(()->new DataNotFoundException(String.format("user is not found with %s",email)));

        return user;

    }


}
