package com.url.linklytics_.shortening.service;


import com.url.linklytics_.shortening.model.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {


    private static final long serialVersionUId=1L;
    private Long id;
    private String userName;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority>grantedAuthorities;

    public UserDetailsImpl(Long id, String userName, String email, String password,
                           Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }


    public static UserDetailsImpl build(User user){
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole());
        return new UserDetailsImpl(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(grantedAuthority)
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }
}
