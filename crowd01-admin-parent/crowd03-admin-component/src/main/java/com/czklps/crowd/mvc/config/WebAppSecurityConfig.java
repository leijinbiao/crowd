package com.czklps.crowd.mvc.config;

import com.czklps.crowd.constant.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
//        builder
//                .inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("jerry")
//                .authorities("ADMIN")
//                .password(new BCryptPasswordEncoder().encode("123456"));

        builder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/admin/login", "/static/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/admin/login")
                .loginProcessingUrl("/admin/doLogin")
                .usernameParameter("loginAcct")
                .passwordParameter("userPswd")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login");
    }
}
