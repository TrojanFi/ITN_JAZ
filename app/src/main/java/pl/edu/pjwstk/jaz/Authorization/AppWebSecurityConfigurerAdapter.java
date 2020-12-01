package pl.edu.pjwstk.jaz.Authorization;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
//                .antMatchers("/admin").hasAnyAuthority("Admin")
//                .antMatchers("/users").hasAnyAuthority("User")
                .antMatchers("/auth0/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();

    }
}