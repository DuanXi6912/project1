package vissoft.example.demo.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import vissoft.example.demo.Model.Dto.PersonDto;
import vissoft.example.demo.Model.Dto.UpdatePersonDto;
import vissoft.example.demo.Model.Entity.Person;
import vissoft.example.demo.Service.ConvertDataService;
import vissoft.example.demo.Service.PersonService;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private ConvertDataService convert;

    @GetMapping("/persons")
    public ResponseEntity<List<PersonDto>> getPersons(){    
        List<PersonDto> persons = personService.getPersons();
        return ResponseEntity.ok().body(persons);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<PersonDto> getPersonByID(@PathVariable int id){
        Person person = personService.getPersonByID(id);
        PersonDto personDto = convert.toPersonDto(person);
        return ResponseEntity.ok().body(personDto);
    }

    @PostMapping("/persons")
    public ResponseEntity<PersonDto> createPerson(@Valid @RequestBody PersonDto person){
        PersonDto personDto = personService.createPerson(person);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                                                    path("/{id}" ).
                                                    buildAndExpand(person.getPersonID()).
                                                    toUri();
        return ResponseEntity.created(location).body(personDto);
    }

    @PutMapping("/persons")
    public ResponseEntity<UpdatePersonDto> updatePerson(@Valid @RequestBody UpdatePersonDto person){
        UpdatePersonDto newPerson = personService.updatePerson(person);
        return ResponseEntity.ok(newPerson);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Void> deletePersonByID(@PathVariable int id){
        personService.deletePersonByID(id);
        return ResponseEntity.noContent().build();
    }
    
}
