package com.cep.corporateeventplanner.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{

    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        // http.anonymous().disable();
        http.authorizeRequests()
                .antMatchers("/",                       // h2
                        "/h2-console/**",          // h2
                        "/v2/api-docs",            // swagger
                        "/swagger-resources",      // swagger
                        "/swagger-resources/**",   // swagger
                        "/configuration/ui",       // swagger
                        "/configuration/security", // swagger
                        "/swagger-ui.html",        // swagger
                        "/webjars/**"   ,
                        "/login",
                        "/signup",
                        "/error"
                ).permitAll()
                .antMatchers("/users/**", "/events/**").authenticated()
                .antMatchers("/signup").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/roles", "/actuator/**", "/**").hasAnyRole("USER")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());

        // http.requiresChannel().anyRequest().requiresSecure();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}