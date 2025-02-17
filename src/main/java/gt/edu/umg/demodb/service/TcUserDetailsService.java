package gt.edu.umg.demodb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gt.edu.umg.demodb.jwt.UserPrinciple;
import gt.edu.umg.demodb.model.TcRole;
import gt.edu.umg.demodb.model.TcUser;
import gt.edu.umg.demodb.repository.TcUserRepository;

@Service
public class TcUserDetailsService implements UserDetailsService {

    @Autowired
    TcUserRepository tcUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TcUser tcUser = tcUserRepository.findByUsername(username).orElseThrow(
            () -> new UsernameNotFoundException("Usuario no existe: " + username)
        );
        List<TcRole> roles = new ArrayList<>();
        roles.add(tcUser.getTcRole());
        return UserPrinciple.build(tcUser, roles);
    }
}