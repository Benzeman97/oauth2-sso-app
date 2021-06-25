package com.benz.oauth2.sso.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

@Configuration
public class OAuth2SsoConfig {


    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository( this.googleClientRegistration(),
                this.gitHubClientRegistration(),this.oktaClientRegistration(),this.linkedinClientRegistration(),
        this.facebookClientRegistration(),this.instagramClientRegistration());
    }


    private ClientRegistration googleClientRegistration(){
        return ClientRegistration.withRegistrationId("google")
                .clientId("832872607191-4rinplsivu9k52bigh4vkevovtif77qs.apps.googleusercontent.com")
                .clientSecret("fTO6XKEKQtqvV0h8_YNJ1cJu")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://127.0.0.1:9098/login/oauth2/code/google")
                .scope("openid","profile", "email", "address", "phone")
               .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
               .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .clientName("Google")
                .build();

    }

    private ClientRegistration gitHubClientRegistration(){
          return ClientRegistration.withRegistrationId("github")
                  .clientId("f82c44488935ae07f615")
                  .clientSecret("9b8db5b5c2269fbd506d92a4681572e604eb689d")
                  .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                  .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                  .redirectUri("http://127.0.0.1:9098/login/oauth2/code/github")
                  .scope("read:user")
                  .authorizationUri("https://github.com/login/oauth/authorize")
                  .tokenUri("https://github.com/login/oauth/access_token")
                  .userInfoUri("https://api.github.com/user")
                  .userNameAttributeName("id")
                  .clientName("Github")
                  .build();
    }

    private ClientRegistration oktaClientRegistration(){
        return ClientRegistration.withRegistrationId("okta")
                .clientId("0oa12c331oTvQ24hL5d7")
                .clientSecret("4BVbsT3VEf90MF6WRuIKDIZjyKWXeFYaYlbaw7VG")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://127.0.0.1:9098/login/oauth2/code/okta")
                .scope("openid","profile","email")
                .authorizationUri("https://dev-10260890.okta.com/oauth2/v1/authorize")
                .tokenUri("https://dev-10260890.okta.com/oauth2/v1/token")
                .userInfoUri("https://dev-10260890.okta.com/oauth2/v1/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .jwkSetUri("https://dev-10260890.okta.com/oauth2/v1/keys")
                .clientName("Okta")
                .build();
    }

    private ClientRegistration linkedinClientRegistration(){
        return ClientRegistration.withRegistrationId("linkedin")
                .clientId("86fmybphtb4gaj")
                .clientSecret("et1DWDnChLqltA64")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://127.0.0.1:9098/login/oauth2/code/linkedin")
                .scope("r_liteprofile","r_emailaddress")
                .authorizationUri("https://www.linkedin.com/oauth/v2/authorization")
                .tokenUri("https://www.linkedin.com/oauth/v2/accessToken")
                .userInfoUri("https://api.linkedin.com/v2/me")
                .clientName("Linkedin")
                .build();
    }


   private ClientRegistration facebookClientRegistration(){
        return ClientRegistration.withRegistrationId("facebook")
                .clientId("1485080065164533")
                .clientSecret("95919d1b3055f9454bb9b25750d2eed5")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://127.0.0.1:9098/login/oauth2/code/facebook")
                .scope("public_profile","email")
                .authorizationUri("https://www.facebook.com/v11.0/dialog/oauth")
                .tokenUri("https://graph.facebook.com/v11.0/oauth/access_token")
                .userInfoUri("https://graph.facebook.com/me")
                .clientName("Facebook")
                .build();
   }

   private ClientRegistration instagramClientRegistration(){
        return ClientRegistration.withRegistrationId("instagram")
                .clientId("164216025625652")
                .clientSecret("fdedb47bd6d4b8eeca31dc5db3532abe")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://127.0.0.1:9098/login/oauth2/code/instagram")
                .scope("user_profile")
                .authorizationUri("https://api.instagram.com/oauth/authorize")
                .tokenUri("https://api.instagram.com/oauth/access_token")
                .userInfoUri("https://api.instagram.com/v1/users/selfl")
                .clientName("Instagram")
                .build();
   }
}
