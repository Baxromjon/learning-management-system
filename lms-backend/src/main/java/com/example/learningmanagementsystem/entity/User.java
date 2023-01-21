package com.example.learningmanagementsystem.entity;

import com.example.learningmanagementsystem.entity.template.AbsEntity;
import com.example.learningmanagementsystem.enums.GenderEnums;
import com.example.learningmanagementsystem.utils.ColumnName;
import com.example.learningmanagementsystem.utils.EntityName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = EntityName.USERS)
@SQLDelete(sql = "UPDATE " + EntityName.USERS + " SET deleted = TRUE WHERE id=?")
@Where(clause = "deleted=false")
public class User extends AbsEntity implements UserDetails {

    @Column(name = ColumnName.FIRST_NAME)
    private String firstName;

    @Column(name = ColumnName.LAST_NAME)
    private String lastName;

    @Column(nullable = false, name = ColumnName.PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = ColumnName.PASSWORD)
    private String password;

    @Column(name = ColumnName.BIRTHDATE)
    private Date birthDate;

    @Column(name = ColumnName.EMAIL)
    private String email;

    @OneToOne
    private Gender gender;

    private String parentKey;

    @ManyToOne
    private User parentId;

    @ManyToMany
    @JoinTable(name = ColumnName.USER_ROLE,
            joinColumns = {@JoinColumn(name = ColumnName.USER_ID)},
            inverseJoinColumns = {@JoinColumn(name = ColumnName.ROLE_ID)})
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>(roles);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.phoneNumber;
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
