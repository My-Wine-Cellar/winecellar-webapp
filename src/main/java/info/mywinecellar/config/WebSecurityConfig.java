/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Public access to login, landing, and error pages
        http.authorizeRequests().antMatchers("/", "/login", "/errorpage").permitAll();

        // Public access to countries, grapes, wines, wine reviews, tasting notes, and producers
        http.authorizeRequests().antMatchers("/d/**", "/grape/**", "/review/**", "/tastingnotes/**").permitAll();

        // Static resource permissions
        http.authorizeRequests()
                .antMatchers("/css/**", "/fonts/**", "/images/**",
                        "/webfonts/**", "/js/**", "/webjars/**", "/messages/**")
                .permitAll();

        // Login specifications
        http.formLogin().loginPage("/login").defaultSuccessUrl("/welcome", true);

        //Logout specifications
        http
                .logout()
                .deleteCookies("remove")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll();

        // Admin only access
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        // Csrf protection
        http.exceptionHandling().and().csrf().disable();

        return http.build();
    }
}
