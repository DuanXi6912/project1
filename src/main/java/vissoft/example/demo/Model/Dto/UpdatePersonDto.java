package vissoft.example.demo.Model.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vissoft.example.demo.Exception.AnnotationCustom.RoleCheck;

@NoArgsConstructor
@Getter
@Setter
public class UpdatePersonDto {
    @NotNull(message = "Fill PersonID")
    private int personID;
    @Pattern(regexp = "^(\\+84|0)\\d{9,10}$", message = "Please match the requested format")
    // Do @PhoneDuplicate là ta check với database nên trường hợp không dùng được :> 
    private String personPhone;
    @RoleCheck(message = "Role Invalid")
    private String personRole;
}
