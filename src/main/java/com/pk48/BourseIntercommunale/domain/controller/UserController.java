package com.pk48.BourseIntercommunale.domain.controller;

import com.pk48.BourseIntercommunale.domain.dto.AuthentificationRequest;
import com.pk48.BourseIntercommunale.domain.dto.AuthentificationResponse;
import com.pk48.BourseIntercommunale.domain.dto.RegistrationRequest;
import com.pk48.BourseIntercommunale.domain.service.AuthentificationService;
import com.pk48.BourseIntercommunale.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final AuthentificationService service;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthentificationResponse> register(@RequestBody RegistrationRequest req) {
        System.out.println("input: "+req.toString());
        return ResponseEntity.ok(service.register(req));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthentificationResponse> authenticate(@RequestBody AuthentificationRequest req) {
        return ResponseEntity.ok(service.authenticate(req));
    }
}
