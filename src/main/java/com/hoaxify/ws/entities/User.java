package com.hoaxify.ws.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.hoaxify.ws.annotationsPakage.UniqueUsername;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Entity(name = "USER")
public class User implements UserDetails {
    /**
     *
     */
    private static final long serialVersionUID = 1964509190748585785L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "{hoaxify.validation.constraint.username.NotNull.message}")
    @Size(min = 4,max=255)
    @UniqueUsername
    @JsonView(Views.Base.class)
    @Column(name = "USERNAME")
    private String username;

    @NotNull
    @Size(min = 4)
    @Column(name = "DISPLAYNAME")
    @JsonView(Views.Base.class)
    private String displayName;


    @NotNull
    @Size(min = 8,max=255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",message = "{hoaxify.validation.constraint.password.Pattern.message}")
    @Column(name = "PASSWORD")
    private String password;

    @JsonView(Views.Base.class)
    @Column(name = "IMAGE")
    private String image;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("Role_user");

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
