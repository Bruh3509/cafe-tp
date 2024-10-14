package com.pet.cafe.controller;

import com.pet.cafe.dto.UserDTO;
import com.pet.cafe.entity.User;
import com.pet.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> createUser(@PathVariable String id, @RequestBody UserDTO request){
        service.createUser(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id){
        return new ResponseEntity<>(service.getUser(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(String id, @RequestBody UserDTO userDTO){
        service.updateUser(id, userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
