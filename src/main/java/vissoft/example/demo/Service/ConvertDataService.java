package vissoft.example.demo.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vissoft.example.demo.Model.Dto.PersonDto;
import vissoft.example.demo.Model.Dto.UpdatePersonDto;
import vissoft.example.demo.Model.Entity.Person;

@Service
public class ConvertDataService {
    @Autowired
    private ModelMapper mapper;

    public PersonDto toPersonDto(Person person) {
        return mapper.map(person, PersonDto.class);
    }

    public Person toPerson(PersonDto personDto) {
        Person person = new Person();
        mapper.map(personDto, person);
        return person;
    }
    
    public UpdatePersonDto toUpPersonDto(Person person){
        UpdatePersonDto updatePerson = new UpdatePersonDto();
        mapper.map(person, updatePerson);
        return updatePerson;
    }
}
