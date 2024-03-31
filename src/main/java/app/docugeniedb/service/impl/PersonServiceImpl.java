package app.docugeniedb.service.impl;

import app.docugeniedb.dto.PersonDTO;
import app.docugeniedb.entity.Person;
import app.docugeniedb.exception.ResourceNotFoundException;
import app.docugeniedb.mapper.PersonMapper;
import app.docugeniedb.repository.PersonRepository;
import app.docugeniedb.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Override
    public PersonDTO createPerson(PersonDTO personDTO) {
        Person person = PersonMapper.mapToPerson(personDTO);
        Person savedPerson = personRepository.save(person);
        return PersonMapper.mapToPersonDTO(savedPerson);
    }

    @Override
    public PersonDTO getPersonById(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Person do not exist with given id: " + personId));
        return PersonMapper.mapToPersonDTO(person);
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        List<Person> personList = personRepository.findAll();
        return personList.stream().map(PersonMapper::mapToPersonDTO).toList();
    }

    @Override
    public PersonDTO updatePerson(Long personId, PersonDTO updatedPerson) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Person do not exist with given id: " + personId));
        person.setUserName(updatedPerson.getUsername());
        person.setPassword(updatedPerson.getPassword());
        Person savedPerson = personRepository.save(person);
        return PersonMapper.mapToPersonDTO(savedPerson);
    }

    @Override
    public void deletePerson(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Person do not exist with given id: " + personId));
        personRepository.deleteById(personId);
    }
}
