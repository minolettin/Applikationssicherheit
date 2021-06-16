package ch.gibb.applikationssicherheit.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(	name = "authorities")
public class Authority implements Serializable {
    @EmbeddedId
    private AuthorityPrimaryKey authorityPrimaryKey;

    @ManyToOne
    private User user;
}
