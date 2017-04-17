package kr.ac.hansung.maldives.web.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleSocialUserDetailsService implements SocialUserDetailsService {

    private UserDetailsService userDetailsService;

    public SimpleSocialUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
        log.debug("Loading user by user id: {}", userId);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        log.debug("Found user details: {}", userDetails);

        return (SocialUserDetails) userDetails;
    }
}