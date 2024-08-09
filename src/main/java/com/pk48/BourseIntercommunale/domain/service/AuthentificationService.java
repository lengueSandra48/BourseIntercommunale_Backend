package com.pk48.BourseIntercommunale.domain.service;

import com.pk48.BourseIntercommunale.domain.dto.AuthentificationRequest;
import com.pk48.BourseIntercommunale.domain.dto.AuthentificationResponse;
import com.pk48.BourseIntercommunale.domain.dto.RegistrationRequest;
import com.pk48.BourseIntercommunale.domain.model.User;
import com.pk48.BourseIntercommunale.domain.model.enumeration.Role;
import com.pk48.BourseIntercommunale.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthentificationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthentificationResponse register(RegistrationRequest req) {
        User user = User.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(Role.USER)
                .phone(req.getPhone())
                .build();
        repository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthentificationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthentificationResponse authenticate(AuthentificationRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword())
        );

        User user = repository.findByEmail(req.getEmail())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        return AuthentificationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
