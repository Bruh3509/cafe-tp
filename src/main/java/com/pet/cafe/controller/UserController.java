package com.pet.cafe.controller;

import com.pet.cafe.dto.UserDTO;
import com.pet.cafe.service.UserService;
import com.pet.cafe.entity.User;
import com.pet.cafe.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    //TODO UserDTO to User
    private final UserServiceImpl service;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserDTO>> getUsers() {
        log.debug("Received request to get all users.");
        var users = service.getUsers();
        log.info("Successfully retrieved users.");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDTO request) {
        log.debug("Received request to create a user.");
        service.createUser(request);
        log.info("Successfully created user.");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable long id) {
        log.debug("Received request to get the user with id {}.", id);
        var user = service.getUser(id);
        log.info("Successfully retrieved user with id {}.", id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        log.debug("Received request to delete the user with id {}.", id);
        service.deleteUser(id);
        log.info("Successfully deleted user with id {}.", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable long id, @RequestBody UserDTO userDTO) {
        log.debug("Received request to update the user with id {}.", id);
        service.updateUser(id, userDTO);
        log.info("Successfully updated user with id {}.", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User curUser = (User) authentication.getPrincipal();
        UserDTO userDTO = convertToDto(curUser); // Используйте DTO
        return ResponseEntity.ok(userDTO);
    }


    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> allUsers() {
        List<User> users = service.allUsers();
        ArrayList<UserDTO> list = new ArrayList<>();
        for (User us : users) {
            list.add(convertToDto(us));
        }

        return ResponseEntity.ok(list);
    }

    public UserDTO convertToDto(User user) {
        return new UserDTO(user.getEmail(), user.getFirstName(), user.getSecondName(), user.getLastName(), user.getPhoneNumber(), user.getPassword(), user.getUsername());
    }
}
