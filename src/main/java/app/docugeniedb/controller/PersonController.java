package app.docugeniedb.controller;

import app.docugeniedb.dto.PersonDTO;
import app.docugeniedb.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private PersonService personService;

    public record PersonResponseDTO(Long id, String username) {
    }

    @PostMapping
    public ResponseEntity<PersonResponseDTO> createPerson(@RequestBody PersonDTO personDTO) {
        PersonDTO savedPersonDTO = personService.createPerson(personDTO);
        PersonResponseDTO personResponseDTO = new PersonResponseDTO(savedPersonDTO.getPerson_id(), savedPersonDTO.getUsername());
        return new ResponseEntity<>(personResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable("id") Long personId) {
        PersonDTO savedPersonDTO = personService.getPersonById(personId);
        return ResponseEntity.ok(savedPersonDTO);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<PersonResponseDTO> getPersonByUsername(@PathVariable("username") String username) {
        PersonDTO personDTO = personService.getPersonByUsername(username);
        PersonResponseDTO personResponseDTO = new PersonResponseDTO(personDTO.getPerson_id(), personDTO.getUsername());
        return ResponseEntity.ok(personResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        List<PersonDTO> personDTOS = personService.getAllPersons();
        return ResponseEntity.ok(personDTOS);
    }

    @PutMapping("{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable("id") Long personId, @RequestBody PersonDTO personDTO) {
        PersonDTO updatedPersonDTO = personService.updatePerson(personId, personDTO);
        return ResponseEntity.ok(updatedPersonDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePerson(@PathVariable("id") Long personId) {
        personService.deletePerson(personId);
        return ResponseEntity.ok("Person deleted successfully!");
    }
}
