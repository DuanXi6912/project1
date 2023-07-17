package vissoft.example.demo.Model;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper ModelMapper(){
        ModelMapper mapper = new ModelMapper();
        // Cấu hình cho đối tượng mapper với kểu "liên kết chặt" 
        // tên/ kiểu dữ liệu thuộc tính nguồn - giống với đích 
        mapper.getConfiguration()
              .setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

}
