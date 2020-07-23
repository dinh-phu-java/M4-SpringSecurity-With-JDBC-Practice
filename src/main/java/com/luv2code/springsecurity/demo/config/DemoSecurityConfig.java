package com.luv2code.springsecurity.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource securityDataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


//        auth.jdbcAuthentication().dataSource(securityDataSource);

        auth.jdbcAuthentication().dataSource(securityDataSource)
		.usersByUsernameQuery(
		"select username, password, enabled from users " +
		"where username=?")
		.authoritiesByUsernameQuery(
		"select username, authority from authorities " +
		"where username=?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/employees/").hasRole("EMPLOYEE")
                .antMatchers("/leaders/").hasRole("MANAGER")
                .antMatchers("/systems/").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")  // after logout redirect to landing page (root)
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }


}

//auth.jdbcAuthentication().dataSource(myDataSource)
//		.usersByUsernameQuery(
//		"select username, password, enabled from users " +
//		"where username=?")
//		.authoritiesByUsernameQuery(
//		"select username, authority from authorities " +
//		"where username=?");