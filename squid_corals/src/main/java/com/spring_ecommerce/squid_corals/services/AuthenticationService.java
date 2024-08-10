package com.spring_ecommerce.squid_corals.services;



import com.spring_ecommerce.squid_corals.models.User;
import com.spring_ecommerce.squid_corals.repository.UserRepository;
import com.spring_ecommerce.squid_corals.securityModels.AuthenticationRequest;
import com.spring_ecommerce.squid_corals.securityModels.AuthenticationResponse;
import com.spring_ecommerce.squid_corals.securityModels.RegisterRequest;
import com.spring_ecommerce.squid_corals.securityModels.Role;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    Logger logger = LogManager.getLogger(AuthenticationService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        String message;
        boolean emailAlreadyExists = userRepository.existsByEmail(request.getEmail());

        if(!emailAlreadyExists){
            User user = new com.spring_ecommerce.squid_corals.models.User();
            user.setEmail(request.getEmail());
            user.setRoles(Collections.singleton(request.getRole()));

            if(user.getRoles().contains(Role.ADMIN)){
                message = "Created Admin User";
            }
            else{
                message = "Created User";
            }

            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            logger.info("User Registered");

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .message(message).build();

        }
        else {
            return AuthenticationResponse.builder()
                    .token(null)
                    .message("Email already in use").build();
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        String message;
        logger.info("Initialing Authentication.......");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        logger.info("User's email: " + user.getEmail());
        var jwtToken = jwtService.generateToken(user);
        logger.info("JWT token Generated");

        if(user.getRoles().contains(Role.ADMIN)){
            message = "Authenticated Admin";
        }
        else{
            message = "Authenticated User";
        }

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .message(message)
                .build();
    }
}
