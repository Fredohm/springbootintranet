package org.fredohm.springbootintranet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> {
            authorize
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers("/index", "/login", "/resources/**").permitAll();
        })
                .authorizeRequests()
                .anyRequest().authenticated()
                    .and()
                    .formLogin().and()
                    .httpBasic();

        http.headers().frameOptions().disable();
        http.csrf().ignoringAntMatchers("/h2-console/**");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("{bcrypt}$2a$10$P0uQenAGMq5XmFffvzDs/O88GQlnkaVPgfbFk2cluvY51pBVDDyAW")
//                .roles("ADMIN");
//
//        auth.inMemoryAuthentication()
//                .withUser("manager")
//                .password("{bcrypt}$2a$10$KTvBNi/3/frqLaGlit1LkesMKHdUPB.TuZ6hjZL8D47T/hKhTR3q2")
//                .roles("MANAGER");
//
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("{bcrypt}$2a$10$lfU5vxh0Vc5y8x3nZ2Ew7OyaNajBhidzn7Njdo5chfpIGKdnzpSJO")
//                .roles("USER");
//    }
}
