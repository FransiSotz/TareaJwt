package com.sourcecode.tareajwt.Auth;

import com.sourcecode.tareajwt.User.Role;
import com.sourcecode.tareajwt.User.UserRepository;
import com.sourcecode.tareajwt.User.User;
import com.sourcecode.tareajwt.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserDetails userDetails = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
    public AuthResponse register(RegisterRequest request) {
        User usuario = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .lastname(request.getLastName())
                .firstname(request.getFirstName())
                .country(request.getCountry())
                .role(Role.USER)
                .build();
        userRepository.save(usuario);
        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }
}
