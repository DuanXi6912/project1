package vissoft.example.demo.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vissoft.example.demo.Exception.AnnotationCustom.PhoneDuplicate;
import vissoft.example.demo.Exception.AnnotationCustom.RoleCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PersonDto { 

    private int personID;
    @NotBlank(message = "Fill PersonName")
    // Name không được rỗng hoặc chỉ chứa các khoảng trắng
    private String personName;
    // Cần xử lý vấn đề unique cho Phone Number => tạo ra một annotation "PhoneDuplicate" :>  
    // regulart expression phone theo kiểu +84
    @Pattern(regexp = "^(\\+84|0)\\d{9,10}$", message = "Please match the requested format")
    @PhoneDuplicate
    private String personPhone;
    @RoleCheck(message = "Role Invalid")
    private String personRole;
    // Thống nhất hết vào việc @Valid thay vì throw Exception => Hiển thị cùng một lúc các lỗi thông qua danh sách
}
