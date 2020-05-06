package com.jeonghyeong.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jeonghyeong.repository.UserAuthorityRepository;
import com.jeonghyeong.repository.UserRepository;
import com.jeonghyeong.service.SpringSecurityUserDetailService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
    @Autowired
    private SpringSecurityUserDetailService springSecurityUserDetailService;

    @Autowired
    private PasswordEncoder globalPasswordEncoder;
    
    @Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserAuthorityRepository userAuthorityRepo;

    @Bean
    public PasswordEncoder setUpPasswordEncoder(){
            System.out.println("Encoder Bean");
            Map<String, PasswordEncoder> encoderMap = new HashMap<String, PasswordEncoder>();
            encoderMap.put("bcrypt", new BCryptPasswordEncoder());

            DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoderMap);
            globalPasswordEncoder = passwordEncoder;
            System.out.println(passwordEncoder.getClass());
            return globalPasswordEncoder;
    }


    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Primary
    public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManager();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .userDetailsService(springSecurityUserDetailService)
                    .passwordEncoder(this.setUpPasswordEncoder());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.csrf()
                    .disable();
            http.formLogin();

            http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean(), userRepo, userAuthorityRepo));
            http.authorizeRequests()
            .antMatchers("/user/me").hasAnyRole("USER", "ROLE_USER");


    }



}
