package com.sinfolix.Sai_Samartha.Controller;

import com.sinfolix.Sai_Samartha.DTO.UserDTO;
import com.sinfolix.Sai_Samartha.Service.CustomUserDetailsServiceImpl;
import com.sinfolix.Sai_Samartha.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private   UserService userService;

    @Autowired
    CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;


// Create the new User
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        String result = userService.saveNewUser(userDTO);
        return new  ResponseEntity<>(result, HttpStatus.CREATED);
    }



    @PostMapping("/login")
    public ResponseEntity<UserDetails> login(@RequestBody UserDTO userDTO) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
            UserDetails userDetails =userDetailsService.loadUserByUsername(userDTO.getUsername());
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }
}
