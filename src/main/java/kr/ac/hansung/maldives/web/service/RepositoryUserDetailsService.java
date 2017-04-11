package kr.ac.hansung.maldives.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.ac.hansung.maldives.web.dao.UserRepository;
import kr.ac.hansung.maldives.web.model.CustomUserDetails;
import kr.ac.hansung.maldives.web.model.User;

public class RepositoryUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryUserDetailsService.class);

    private UserRepository repository;

    @Autowired
    public RepositoryUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Loading user by username: {}", username);

        User user = repository.findByEmail(username);
        LOGGER.debug("Found user: {}", user);

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

        LOGGER.debug("Returning user details: {}", principal);

        return principal;
    }
}
