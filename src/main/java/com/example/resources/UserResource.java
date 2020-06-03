package com.example.resources;

import com.example.services.UserService;
import com.example.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserResource {
    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/{id}"
    )
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
}
