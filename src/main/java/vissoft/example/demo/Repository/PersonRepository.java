package vissoft.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vissoft.example.demo.Model.Entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    
}
