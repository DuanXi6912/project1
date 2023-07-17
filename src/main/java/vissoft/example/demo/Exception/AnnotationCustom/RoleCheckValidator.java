package vissoft.example.demo.Exception.AnnotationCustom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleCheckValidator implements ConstraintValidator<RoleCheck, String> {

    public boolean isValid(String role, ConstraintValidatorContext context){
        
        if(role == null || role.isEmpty()){
            return true;
        } else if( role.equals("Admin") || role.equals("Employee") ){
            return true;
        }

        return false;
    }
}
