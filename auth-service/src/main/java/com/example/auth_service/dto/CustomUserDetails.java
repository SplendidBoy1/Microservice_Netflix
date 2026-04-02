package com.example.auth_service.dto;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails{
    
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String username;

    @Override
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


    private String password;

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private List<GrantedAuthority> authorities;

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public CustomUserDetails(){

    }

    public CustomUserDetails(Long id, String username, String password, List<GrantedAuthority> authorities){
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }


}
