package com.benz.oauth2.sso.api.dao;

import com.benz.oauth2.sso.api.entity.OAuth2User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuth2UserDao extends JpaRepository<OAuth2User,String> {

   Optional<OAuth2User> findOAuth2UserByOauth2Provider(String provider);
}
