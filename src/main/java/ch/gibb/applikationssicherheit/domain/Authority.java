package ch.gibb.applikationssicherheit.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(	name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 50)
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "authority")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<PersonAuthority> personAuthorities = new HashSet<>();
}
