package com.gabriel.moraes.eventos.domain.user;

import com.gabriel.moraes.eventos.domain.auth.JwtService;
import com.gabriel.moraes.eventos.domain.user.exceptions.EmailAlreadyExistsException;
import com.gabriel.moraes.eventos.domain.user.payloads.UserAuthenticateRequest;
import com.gabriel.moraes.eventos.domain.user.payloads.UserRequest;
import com.gabriel.moraes.eventos.domain.user.payloads.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;


    public UserResponse registerUser(UserRequest userRequest) {
        userRequest.setPassword(encoder.encode(userRequest.getPassword()));

        validateEmailUniqueness(userRequest.getEmail());

        User user = mapper.map(userRequest, User.class);
        return mapper.map(userRepository.save(user), UserResponse.class);
    }

    public String authenticate(UserAuthenticateRequest userAuthenticateRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthenticateRequest.getEmail(),
                        userAuthenticateRequest.getPassword()
                )
        );

        userDetailsService.loadUserByUsername(userAuthenticateRequest.getEmail());

        return jwtService.generateToken(authentication);
    }

    public void validateEmailUniqueness(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Email already exists: " + email);
        }
    }

}