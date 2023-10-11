package edu.projetosecurity.projetocomsecurity.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.projetosecurity.projetocomsecurity.model.User;
import edu.projetosecurity.projetocomsecurity.repository.UserRepository;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
@Service
public class SecurityDatabaseService  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        User userEntity = userRepository.findByUserName(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        userEntity.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        });

        UserDetails user = new org.springframework.security.core.userdetails.User(userEntity.getUserName(),
                userEntity.getPassword(),
                authorities);
        return user;
    }
}