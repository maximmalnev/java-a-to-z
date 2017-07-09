package ru.kovladimir.springcars.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.kovladimir.springcars.service.HibernateAuth;

/**
 * WebSecurityConfig.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final HibernateAuth hibernateAuth;

    @Autowired
    public WebSecurityConfig(HibernateAuth hibernateAuth) {
        this.hibernateAuth = hibernateAuth;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(hibernateAuth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/add", "/id*/edit/**", "/id*/delete/**").authenticated()
                .and().formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                .and().logout().logoutSuccessUrl("/")
                .and().httpBasic()
                .and().csrf().disable();
    }


}
