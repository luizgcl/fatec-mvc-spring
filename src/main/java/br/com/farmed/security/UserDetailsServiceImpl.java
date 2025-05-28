package br.com.farmed.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.farmed.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginUser) throws UsernameNotFoundException {
        return userRepository.findByNameUser(loginUser)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
