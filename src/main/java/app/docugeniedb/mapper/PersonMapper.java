package app.docugeniedb.mapper;

import app.docugeniedb.dto.PersonDTO;
import app.docugeniedb.entity.Person;

public class PersonMapper {

    public static PersonDTO mapToPersonDTO(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getUserName(),
                person.getPassword()
        );
    }

    public static Person mapToPerson(PersonDTO personDTO) {
        return new Person(
                personDTO.getId(),
                personDTO.getUsername(),
                personDTO.getPassword()
        );
    }
}
