package io.github.gnobroga.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> System.out.println("Hello World");
    }
    
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


    @Bean
    UserDetailsService users() {
        UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
            .username("guest")
            .password("guest")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

 
}
