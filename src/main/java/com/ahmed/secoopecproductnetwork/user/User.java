package com.ahmed.secoopecproductnetwork.user;


import com.ahmed.secoopecproductnetwork.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="User")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal{
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(r->new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

    @Id
    @GeneratedValue
private Integer id;
    private String firstname;
    private String lastname;
    private LocalTime dateOfBirth;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean accountLocked;
    private boolean enabled;

    //liste of roles

@CreatedDate
@Column(nullable = false,updatable = false)
 private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false )
 private LocalDateTime LastModifiedDate;


    @ManyToMany(fetch = FetchType.EAGER)
    List<Role> roles;


    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getName() {
        return email;
    }

   @OneToMany (mappedBy = "user")
   List <Token> tokens;
    private String getfullname(){
        return firstname + " " +lastname;
    }
}
