package com.iamneo.lms.lms.service.implementations;

import java.util.Map;
import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.iamneo.lms.lms.dto.request.LoginRequest;
import com.iamneo.lms.lms.dto.response.BasicResponse;
import com.iamneo.lms.lms.dto.response.LoginResponse;
import com.iamneo.lms.lms.enumerated.Role;
import com.iamneo.lms.lms.model.Token;
import com.iamneo.lms.lms.model.User;
import com.iamneo.lms.lms.repository.StudentProfileRepository;
import com.iamneo.lms.lms.repository.TokenRepository;
import com.iamneo.lms.lms.repository.UserRepository;
import com.iamneo.lms.lms.service.AuthenticationService;
import com.iamneo.lms.lms.utils.JwtUtil;
import com.iamneo.lms.lms.utils.Exception.ProfileNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final StudentProfileRepository studentProfileRepository;

    @Override
    public BasicResponse<LoginResponse> login(LoginRequest loginRequest) throws ProfileNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword()));

        var userExists = userRepository.findByUserId(loginRequest.getUserId()).orElseThrow();
        Map<String, Object> claims = new HashMap<>();
        System.out.println("\n\n\n" + userExists.getRole().toString() + "\n\n\n");
        claims.put("role", userExists.getRole().toString());
        String accessToken = jwtUtil.generateToken(userExists, claims);
        revokeAllUserTokens(userExists);
        saveUserToken(accessToken, userExists);
        if (userExists.getRole().equals(Role.STUDENT)) {
            studentProfileRepository.findByUser_Id(userExists.getId()).orElseThrow(
                    () -> new ProfileNotFoundException(
                            "The student profile does not exists, Please create a profile first"));
        } else if (userExists.getRole().equals(Role.TEACHER)) {
            throw new ProfileNotFoundException("Teacher profile is still under construction");
        }
        return BasicResponse.<LoginResponse>builder()
                .message("User logged in successfully")
                .data(
                        LoginResponse.builder().accessToken(accessToken)
                                .build())
                .build();
    }

    private void saveUserToken(String accessToken, User user) {
        var token = Token.builder()
                .token(accessToken)
                .user(user)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllByUser_IdAndRevokedFalseAndExpiredFalse(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}
