package ch.gibb.applikationssicherheit.repository;

import ch.gibb.applikationssicherheit.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Boolean existsByUsername(String username);

    Optional<Person> findByUsername(String username);
}
