package vissoft.example.demo.Exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// Một thông báo lỗi cơ bản bao gồm HttpStatus và message 
public class ErrorRespone {
    private HttpStatus status;
    private String message;
}
