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

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
        PersonDTO savedPersonDTO = personService.createPerson(personDTO);
        return new ResponseEntity<>(savedPersonDTO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable("id") Long personId) {
        PersonDTO savedPersonDTO = personService.getPersonById(personId);
        return ResponseEntity.ok(savedPersonDTO);
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
