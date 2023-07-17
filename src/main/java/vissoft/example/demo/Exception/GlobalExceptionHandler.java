package vissoft.example.demo.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    // Do khi create hay Update ta sẽ có các trường cần được kiểm tra - @valid
    // Ta trả về mã lỗi tương ứng là 400 với message là danh sách các trường bị lỗi format
    // PersonDto cho Create và Get đảm bảo thông tin là cần thiết
    // UpdatePersonDto cho Update đầy đủ thông tin (có thể sửa) và ID để xác định trong database 
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
        .getFieldErrors()
        .forEach(
            (error) -> { errors.put(error.getField(), error.getDefaultMessage()); }
        );
        return errors;
    }

    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PersonNotFoundException.class)
    // Ta trả về lỗi PersonNotFoundException với HttpStatus.NOT_FOUND - 404 
    // và Message về vấn đề ID - nguyên nhân khiến lỗi xảy ra
    public ErrorRespone PersonNotFoundException(PersonNotFoundException ex){
        return new ErrorRespone(HttpStatus.NOT_FOUND, ex.getMessage());
    }

}
