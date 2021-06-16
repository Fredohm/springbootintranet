package org.fredohm.springbootintranet.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toH2Console());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // give access to mvc app static resources
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources()
                        .atCommonLocations())
                .permitAll()
                .and()
                // secure access to swagger-ui and postman
                .authorizeRequests()
                .antMatchers("/api/**")
                .fullyAuthenticated()
                .and()
                // secure access to MVC application
                .authorizeRequests(authorize -> {
                    authorize
                            .antMatchers("/", "/index", "/login")
                            .permitAll();
                })
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticateUser")
                .defaultSuccessUrl("/index", true)
                .and()
                .logout()
                .logoutUrl("/doLogout")
                .logoutSuccessUrl("/index?logout")
                .permitAll()
                .and()
                .httpBasic();

        // for securing postman use
        http.cors();
    }
}
