package kajitool.web.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("kajitool.dao.mapper")
public class MyBatisConfig {
}
