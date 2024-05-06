package app.docugeniedb.service;

import app.docugeniedb.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    PersonDTO createPerson(PersonDTO personDTO);

    PersonDTO getPersonById(Long personId);

    PersonDTO getPersonByUsername(String username);

    List<PersonDTO> getAllPersons();

    PersonDTO updatePerson(Long personId, PersonDTO updatedPerson);

    void deletePerson(Long personId);
}
