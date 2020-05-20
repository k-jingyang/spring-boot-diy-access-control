package com.jingyang.accesscontrol;

import com.google.common.collect.Lists;
import com.jingyang.accesscontrol.domain.KmoOPAVoter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.expression.WebExpressionVoter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    KmoOPAVoter kmoOPAVoter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/static/**").permitAll().and().authorizeRequests()
                        .anyRequest().authenticated().accessDecisionManager(accessDecisionManager())
                        .and().formLogin();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder().username("saul").password("{noop}password").roles("USER")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        return new UnanimousBased(Lists.newArrayList(new WebExpressionVoter(), kmoOPAVoter));
    }

}
