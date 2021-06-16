package ch.gibb.applikationssicherheit.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class AuthorityPrimaryKey implements Serializable {
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String authority;
}
