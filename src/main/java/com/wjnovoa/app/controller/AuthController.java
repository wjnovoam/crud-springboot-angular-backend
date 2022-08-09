package com.wjnovoa.app.controller;

import com.wjnovoa.app.dto.LoginDTO;
import com.wjnovoa.app.dto.RegisterDTO;
import com.wjnovoa.app.entity.Rol;
import com.wjnovoa.app.entity.User;
import com.wjnovoa.app.repository.RolRepository;
import com.wjnovoa.app.repository.UserRepository;
import com.wjnovoa.app.security.JWTAuthResponseDTO;
import com.wjnovoa.app.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author William Johan Novoa Melendrez
 * @date 4/08/2022
 */

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token  = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) throws Exception {
        Map<String, Object> responseMap = new HashMap<>();


        if(Boolean.TRUE.equals(userRepository.existsByUsername(registerDTO.getUsername()))){
            responseMap.put("message", "That username already exists");
            responseMap.put("status", 400);
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }

        if(Boolean.TRUE.equals(userRepository.existsByEmail(registerDTO.getEmail()))){
            responseMap.put("message", "That email already exists");
            responseMap.put("status", 400);
            return new ResponseEntity<>("That email already exists", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setDocument(registerDTO.getDocument());
        user.setLastname(registerDTO.getLastname());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Rol roles = rolRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new Exception("Role not found"));
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        responseMap.put("data",registerDTO);
        responseMap.put("message", "User created successfully");

        return new ResponseEntity<>(responseMap, HttpStatus.OK);

    }
}