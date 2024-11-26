package com.sinfolix.Sai_Samartha.Controller;

import com.sinfolix.Sai_Samartha.DTO.ApiResponse;
import com.sinfolix.Sai_Samartha.DTO.ProductCatalogueDTO;
import com.sinfolix.Sai_Samartha.DTO.UserDTO;
import com.sinfolix.Sai_Samartha.Service.ProductCatalogueService;
import com.sinfolix.Sai_Samartha.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductCatalogueService productCatalogueService;


// Create the new ADMIN
    @PostMapping("create-admin")
    public ResponseEntity<String> addAdmin(@RequestBody UserDTO user){
        String result = userService.saveAdmin(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<UserDTO> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }





}
