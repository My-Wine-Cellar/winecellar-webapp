/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
        http.authorizeRequests().requestMatchers(HttpMethod.GET, "/", "/login", "/errorpage").permitAll();

        // Logged user edit access
        http.authorizeRequests().requestMatchers(HttpMethod.POST,
                "/country/*/edit", "/grape/*/edit", "/area/*/edit", "/region/*/edit")
                .hasRole("USER");

        // Public access to countries, grapes, wines, wine reviews, tasting notes, and producers
        http.authorizeRequests().requestMatchers(HttpMethod.GET, "/d/**", "/grape/**", "/review/**", "/tastingnotes/**").permitAll();

        // Static resource permissions
        http.authorizeRequests()
                .requestMatchers(HttpMethod.GET, "/css/**", "/fonts/**", "/images/**",
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
        http.authorizeRequests().requestMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN");

        // Csrf protection
        http.exceptionHandling().and().csrf().disable();

        return http.build();
    }
}
