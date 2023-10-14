package com.dxc.assessment.loginapp.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dxc.assessment.loginapp.model.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    final static String SQL_LOAD_USER = "select username, password, enabled, full_name from users where username = ?";
    final static String SQL_LOAD_AUTHORITY = "select authority from authorities where username = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_LOAD_USER, username);

        if (!rs.next()) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }

        String password = rs.getString("password");
        boolean enabled = rs.getBoolean("enabled");
        String fullName = rs.getString("full_name");
        List<String> roles = loadUserAuthorities(username);
        Collection<GrantedAuthority> authorities = roles.stream()
                                            .map(role -> new SimpleGrantedAuthority(role))
                                            .collect(Collectors.toList());
        CustomUserDetails cud = new CustomUserDetails(username, password, enabled, fullName, authorities);
        
        return cud;
    }

    private List<String> loadUserAuthorities(String username) {
        return jdbcTemplate.queryForList(SQL_LOAD_AUTHORITY, String.class, username);
    }
    
}
