package com.spring_ecommerce.squid_corals.securityConfig;


import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    Logger logger = LogManager.getLogger(SecurityConfig.class);


    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

     @Autowired
     private AuthenticationProvider authenticationProvider;

    //When application starts, spring secuirty looks for SecurityFilterChain bean


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.info("Initializing Filter chain........");
        http
                .csrf()
                .disable()

                .authorizeHttpRequests((authz) -> {
                            try {
                                authz
                                        .requestMatchers("/users/**")
                                        .permitAll()
                                        .requestMatchers("/basketItems/**")
                                        .permitAll()
                                        .requestMatchers("/items/**")
                                        .permitAll()
                                        .requestMatchers("/baskets/**")
                                        .permitAll()
                                        .requestMatchers("/orders/**")
                                        .permitAll()
//                                        .requestMatchers("/authenticate")
//                                        .permitAll()
//
                                        .anyRequest()
//                                        .denyAll()
                                        .authenticated()

                                        .and()
                                        .sessionManagement()
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                        .and()
                                        .authenticationProvider(authenticationProvider)
                                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });

        return http.build();
    }
}