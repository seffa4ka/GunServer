package th.seffka.gun.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

@Configuration
@EnableJpaRepositories("th.seffka.gun.server.repository")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan("th.seffka.gun.server")
public class DbConfig {
    @Resource
    public Environment gunEnvironment;
}
