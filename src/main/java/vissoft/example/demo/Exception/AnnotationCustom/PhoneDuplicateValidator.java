package vissoft.example.demo.Exception.AnnotationCustom;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import vissoft.example.demo.Service.PersonService;

//  PhoneDuplicateValidator -  {Tên Class có vai trò validate cho anootation được đăng ký với "validatedBy"}
public class PhoneDuplicateValidator implements ConstraintValidator<PhoneDuplicate, String> {
// ConstraintValidator<{Tên annotation}, {kiểu dữ liệu của trường kiểm tra}>   

    @Autowired
    private PersonService personService;

    // Hàm logic để đánh giá tính đúng đắn của dữ liệu đầu vào cho trường dữ liệu
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !personService.validPhoneDuplicate(value);
    }
}
