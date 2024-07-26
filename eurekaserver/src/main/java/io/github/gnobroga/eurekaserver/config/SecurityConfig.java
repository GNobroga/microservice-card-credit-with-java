package io.github.gnobroga.eurekaserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeRequests( authorizeRequests -> {
                authorizeRequests.anyRequest().authenticated();
            })
            .formLogin(formLogin -> formLogin.disable())
            .httpBasic(Customizer.withDefaults());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        UserDetails guest = userBuilder
            .username("guest")
            .password("guest")
            .roles("USER")
            .build();

        auth.inMemoryAuthentication().withUser(guest);
    }

}
