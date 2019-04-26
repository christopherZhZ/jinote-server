package com.frankc137.jinote.security.impl;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityProxy implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityProxy(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
