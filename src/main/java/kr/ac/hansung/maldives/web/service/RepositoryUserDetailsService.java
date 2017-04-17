package kr.ac.hansung.maldives.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.ac.hansung.maldives.web.dao.UserRepository;
import kr.ac.hansung.maldives.web.model.CustomUserDetails;
import kr.ac.hansung.maldives.web.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepositoryUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public RepositoryUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Loading user by username: {}", username);

        User user = repository.findByEmail(username);
        log.debug("Found user: {}", user);

        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        CustomUserDetails principal = CustomUserDetails.builder()
                .name(user.getName())
                .user_idx(user.getUser_idx())
                .password(user.getPassword())
                .role(user.getRole())
                .socialSignInProvider(user.getSignInProvider())
                .username(user.getEmail())
                .build();

        log.debug("Returning user details: {}", principal);

        return principal;
    }
}
