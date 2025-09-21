package com.inovace.spring_security.controller;

import com.inovace.spring_security.dtos.AuthenticationRequest;
import com.inovace.spring_security.dtos.AuthenticationResponse;
import com.inovace.spring_security.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private JWTUtils jwtUtils;

    @RequestMapping("/Hello")
    public String hello() {
        return "Hello World";
    }


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.username(), authenticationRequest.password()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username());
        final String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }

    }
