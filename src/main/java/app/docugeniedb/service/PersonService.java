package app.docugeniedb.service;

import app.docugeniedb.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    PersonDTO createPerson(PersonDTO personDTO);

    PersonDTO getPersonById(Long personId);

    List<PersonDTO> getAllPersons();

    PersonDTO updatePerson(Long personId, PersonDTO updatedPerson);

    void deletePerson(Long personId);
}
