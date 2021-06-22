package ch.gibb.applikationssicherheit.service.mapper;

import ch.gibb.applikationssicherheit.domain.PersonAuthority;
import ch.gibb.applikationssicherheit.service.dto.PersonAuthorityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonAuthorityMapper extends EntityMapper<PersonAuthorityDTO, PersonAuthority> {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "authority.id", target = "authorityId")
    @Mapping(source = "authority.name", target = "authorityName")
    PersonAuthorityDTO toDto(PersonAuthority personAuthority);

    PersonAuthority toEntity(PersonAuthorityDTO personAuthorityDTO);

    default PersonAuthority fromId(Long id) {
        if (id == null) {
            return null;
        }
        PersonAuthority personAuthority = new PersonAuthority();
        personAuthority.setId(id);
        return personAuthority;
    }
}
