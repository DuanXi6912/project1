package vissoft.example.demo.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import vissoft.example.demo.Exception.PersonNotFoundException;
import vissoft.example.demo.Model.Dto.PersonDto;
import vissoft.example.demo.Model.Dto.UpdatePersonDto;
import vissoft.example.demo.Model.Entity.Person;
import vissoft.example.demo.Repository.PersonRepository;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private ConvertDataService convert; // Service xử lý chuyển đổi Model và Dto
    private Set<String> phoneNumbers;

    public Set<String> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    // Khởi tạo Service và lấy ra danh sách Phone để kiểm tra PhoneDuplicate
    public PersonService(PersonRepository personRepository, ConvertDataService convert) {
        this.personRepository = personRepository;
        this.convert = convert;
        List<Person> persons = personRepository.findAll();
        Set<String> phone = new HashSet<String>();
        for (int i = 0; i < persons.size(); i++) {
            phone.add(persons.get(i).getPersonPhone());
        }
        setPhoneNumbers(phone);
    }

    public List<PersonDto> getPersons() {
        List<Person> persons = personRepository.findAll();
        List<PersonDto> personDtos = new ArrayList<>();
        for (int i = 0; i < persons.size(); i++) {
            personDtos.add(convert.toPersonDto(persons.get(i)));
        }
        return personDtos;
    }

    public Person getPersonByID(int id) {
        if (id <= 0) {
            throw new PersonNotFoundException("ID can't be negative or equal to 0");
        } else if (personRepository.findById(id).isPresent()) {
            return personRepository.findById(id).get();
        }
        throw new PersonNotFoundException("Person with ID = " + id + " Not Found");
    }

    public PersonDto createPerson(PersonDto person) {
        Person newPerson = convert.toPerson(person);
        // @Rolecheck trước khi lưu vào database để được tạo ra PersonID
        // Nếu newPersonRole không phải "Admin" => setRole là "Employee"
        if (newPerson.getPersonRole() == null || newPerson.getPersonRole().isEmpty()) {
            newPerson.setPersonRole("Employee");
        }
        newPerson = personRepository.save(newPerson);

        // Bây giờ PersonID của newPerson đã được khởi tạo
        // => Khởi tạo Mail cho các Person
        newPerson.setPersonEmail("Person" + newPerson.getPersonID() + "@gmail.com");

        // Khởi tạo đầy đủ các thông tin cho một Person, lưu vào trả về Dto
        newPerson = personRepository.save(newPerson);
        return convert.toPersonDto(newPerson);
    }

    public UpdatePersonDto updatePerson(UpdatePersonDto newPerson){
    Person oldPerson = getPersonByID(newPerson.getPersonID());
    // Trường hợp không đổi thì nhập giá trị y hệt là được 
    // Trường hợp thay đổi Số điện thoại (thông dụng) - (chuẩn format/ không trống) và (khác oldPhone và không trùng) 
    String oldPhone = oldPerson.getPersonPhone();
    String newPhone = newPerson.getPersonPhone();
    if(!oldPhone.equals(newPhone) && !validNewPhone(oldPhone, newPhone)){
        oldPerson.setPersonPhone(newPerson.getPersonPhone());
    }
    String oldRole = oldPerson.getPersonRole();
    String newRole = newPerson.getPersonRole();
    //  Trường hợp thay đổi Role - chuẩn format/ không trống và khác oldRole
    if(!oldRole.equals(newRole)){
        oldPerson.setPersonRole(newRole);
    }

    oldPerson = personRepository.save(oldPerson);
    return convert.toUpPersonDto(oldPerson);
    }

    public void deletePersonByID(int id){
        if (id <= 0) {
            throw new PersonNotFoundException("ID can't be negative or equal to 0");
        } else if (personRepository.findById(id).isPresent()) {
            Person person = personRepository.findById(id).get();
            personRepository.delete(person);
        } else {
            throw new PersonNotFoundException("Person with ID = " + id + " Not Found");
        }
    }

    // Phương thức bổ sung
    public boolean validPhoneDuplicate(String phone) {
        return phoneNumbers.contains(phone);
    }

    public boolean validNewPhone(String oldPhone, String newPhone) {
        phoneNumbers.remove(oldPhone);
        return phoneNumbers.contains(newPhone);
    }
}
