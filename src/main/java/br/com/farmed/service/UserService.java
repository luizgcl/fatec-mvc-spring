package br.com.farmed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.farmed.dto.UserDTO;
import br.com.farmed.entity.User;
import br.com.farmed.entity.roles.Role;
import br.com.farmed.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void registerNewUser(UserDTO userDTO) {
        User user = new User();
        user.setNameUser(userDTO.getNameUser());
        user.setEmailUser(userDTO.getEmailUser());
        user.setPasswordUser(passwordEncoder.encode(userDTO.getPasswordUser()));
        user.setRole(Role.USER);
        userRepository.save(user);
    }
}
