package com.irakliM.demo.entities.enums;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.irakliM.demo.entities.enums.Permission.STUDENT_ADD;
import static com.irakliM.demo.entities.enums.Permission.STUDENT_READ;


public enum Role {

    ADMIN(Set.of(STUDENT_READ, STUDENT_ADD));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(i -> new SimpleGrantedAuthority(i.getPermission()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority(name()));
        return authorities;
    }
}
