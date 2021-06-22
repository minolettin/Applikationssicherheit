package ch.gibb.applikationssicherheit.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(	name = "person_authority")
public class PersonAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;
}
