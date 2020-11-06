package ru.mirea.budget.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@EnableMongoHttpSession
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/register")
                .permitAll().anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                .and().formLogin()
                .successHandler((req,res,auth) -> res.setStatus(HttpServletResponse.SC_OK))
                .failureHandler((req,res, exc) -> res.setStatus(HttpServletResponse.SC_FORBIDDEN))
                .and().logout().logoutUrl("/logout")
                .logoutSuccessHandler((req,res,auth) -> res.setStatus(HttpServletResponse.SC_OK))
                .and().userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
