package edu.projetosecurity.projetocomsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.projetosecurity.projetocomsecurity.config.PasswordEncodes;
import edu.projetosecurity.projetocomsecurity.model.User;
import edu.projetosecurity.projetocomsecurity.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncodes passwordEncoders;

    @PostMapping("/create")
    public ResponseEntity<User> createdUser(@RequestBody User user) {
        var reqUser = userRepository.findByUserName(user.getUserName());

        if (reqUser != null) {
            throw new RuntimeException("usuário já existe, tente outro userName!");
        }

        User u = new User();

        u.setName(user.getName());
        u.setUserName(user.getUserName());

        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoders.passwordEncoder().encode(rawPassword);
        u.setPassword(encodedPassword);

        u.getRoles().addAll(user.getRoles());

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.getRoles().add("USERS");

            u.setRoles(user.getRoles());
        }

        userRepository.save(u);

        return ResponseEntity.ok().build();
    }
}
