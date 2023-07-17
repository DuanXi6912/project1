package vissoft.example.demo.Exception;

public class PersonNotFoundException extends RuntimeException {
    
    public PersonNotFoundException(String message) {
            super(message);
    }
}
