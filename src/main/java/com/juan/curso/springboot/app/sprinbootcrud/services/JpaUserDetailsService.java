package com.juan.curso.springboot.app.sprinbootcrud.services;

import com.juan.curso.springboot.app.sprinbootcrud.model.User;
import com.juan.curso.springboot.app.sprinbootcrud.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        User user = userOptional.orElseThrow();

        List<GrantedAuthority> authorities = user.getRole().stream()
                .map(role -> (GrantedAuthority) () -> role.getName())
                .toList();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.isEnabled() ,true,true,true ,authorities);
    }
}
