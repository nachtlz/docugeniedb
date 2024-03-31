package app.docugeniedb.service.impl;

import app.docugeniedb.dto.PersonDTO;
import app.docugeniedb.entity.Person;
import app.docugeniedb.mapper.PersonMapper;
import app.docugeniedb.repository.PersonRepository;
import app.docugeniedb.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
