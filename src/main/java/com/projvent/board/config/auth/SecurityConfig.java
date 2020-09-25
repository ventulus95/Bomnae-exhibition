package com.projvent.board.config.auth;

import com.projvent.board.web.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                        .authorizeRequests()
                        .antMatchers("/","/about","/sign-in","/css/**","/image/**","/js/**", "/h2-console/**", "/assets/**", "/post-save","/api/v1/**","/post/update/**","/gusetbook-list").permitAll()
//                        .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                        .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                        .anyRequest().authenticated()
                .and()
                        .logout()
                            .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }

    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }
}
