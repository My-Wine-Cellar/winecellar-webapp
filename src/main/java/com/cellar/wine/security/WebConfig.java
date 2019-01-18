package com.cellar.wine.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = passwordEncoder();
        auth.inMemoryAuthentication().withUser("user").password(encoder.encode("user")).roles("USER");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        // from SFG
//        http.authorizeRequests().antMatchers("/login","logout").permitAll()
//                .and().authorizeRequests().antMatchers("/static/css/**","/js/**", "/images/**", "/**/favicon.ico").permitAll()
//                .and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
//                .and().logout()
//                .deleteCookies("remove")
//                .invalidateHttpSession(true)
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/logout-success")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//        ;
//
//        // from Chad, sort of
//        http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
//                //.and().authorizeRequests().antMatchers("/messages", "/static/**/**").permitAll()
//                .and().logout().deleteCookies("remove").invalidateHttpSession(true)
//                .logoutUrl("/logout").logoutSuccessUrl("/logout-success").logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
//    }


    //https://memorynotfound.com/spring-boot-spring-security-thymeleaf-form-login-example/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/js/**",
                        "/css/**",
                        "/images/**",
                        "/webjars/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling()
                ;
    }
}
