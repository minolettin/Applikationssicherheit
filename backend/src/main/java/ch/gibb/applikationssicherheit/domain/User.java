package ch.gibb.applikationssicherheit.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User {
    @Id
    private String username;

    private String password;

    @OneToMany
    private Set<Authority> authorities = new HashSet<>();
}
