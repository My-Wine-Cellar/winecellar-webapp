/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.security;

import javax.sql.DataSource;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private DataSource dataSource;

    /**
     * WebSecurityConfig constructor
     *
     * @param bCryptPasswordEncoder bCryptPasswordEncoder
     * @param dataSource            dataSource
     */
    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, DataSource dataSource) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select u.username, a.authority from users u inner join " +
                        "user_authority ua on (u.id=ua.user_id) inner join authority a " +
                        "on(ua.authority_id=a.id) where u.username=?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

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
    }
}
