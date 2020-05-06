package com.jeonghyeong.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.jeonghyeong.service.SpringSecurityClientDetailService;

@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter{




        @Autowired
        private DataSource dataSource;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private SpringSecurityClientDetailService clientDetailService;


        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
                security.tokenKeyAccess("permitAll()")
                                .checkTokenAccess("isAuthenticated()")
                                .allowFormAuthenticationForClients();

        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
                clients.withClientDetails(clientDetailService);
        }


        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
                endpoints.authenticationManager(authenticationManager)
                                 .tokenStore(new JdbcTokenStore(dataSource))
                                 .accessTokenConverter(jwtAccessTokenConverter());
        }


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        return new JwtAccessTokenConverter();
    }



}
