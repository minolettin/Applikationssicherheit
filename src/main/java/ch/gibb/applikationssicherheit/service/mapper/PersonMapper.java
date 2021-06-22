package ch.gibb.applikationssicherheit.service.mapper;

import ch.gibb.applikationssicherheit.domain.Person;
import ch.gibb.applikationssicherheit.service.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {PersonAuthorityMapper.class})
public interface PersonMapper extends EntityMapper<PersonDTO, Person> {

    PersonDTO toDto(Person person);

    Person toEntity(PersonDTO personDTO);

    default Person fromId(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }
}
