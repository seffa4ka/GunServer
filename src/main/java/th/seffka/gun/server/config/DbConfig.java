package th.seffka.gun.server.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("th.seffka.gun.server.repository")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan("th.seffka.gun.server")
public class DbConfig {

    @Resource
    public Environment gunEnvironment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean gunEntityManager = new LocalContainerEntityManagerFactoryBean();
        gunEntityManager.setDataSource(dataSource());
        gunEntityManager.setPackagesToScan(gunEnvironment.getRequiredProperty("db.entity.package"));
        gunEntityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        gunEntityManager.setJpaProperties(getHibernateProperties());

        return gunEntityManager;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource gunDataSource = new BasicDataSource();
        gunDataSource.setUrl(gunEnvironment.getRequiredProperty("db.url"));
        gunDataSource.setDriverClassName(gunEnvironment.getRequiredProperty("db.driver"));
        gunDataSource.setUsername(gunEnvironment.getRequiredProperty("db.username"));
        gunDataSource.setPassword(gunEnvironment.getRequiredProperty("db.password"));

        gunDataSource.setInitialSize(Integer.valueOf(gunEnvironment.getRequiredProperty("db.initialSize")));
        gunDataSource.setMinIdle(Integer.valueOf(gunEnvironment.getRequiredProperty("db.minIdle")));
        gunDataSource.setMaxIdle(Integer.valueOf(gunEnvironment.getRequiredProperty("db.maxIdle")));
        gunDataSource.setTimeBetweenEvictionRunsMillis(Long.valueOf(gunEnvironment.getRequiredProperty("db.timeBetweenEvictionRunsMillis")));
        gunDataSource.setMinEvictableIdleTimeMillis(Long.valueOf(gunEnvironment.getRequiredProperty("db.minEvictableIdleTimeMillis")));
        gunDataSource.setTestOnBorrow(Boolean.valueOf(gunEnvironment.getRequiredProperty("db.testOnBorrow")));
        gunDataSource.setValidationQuery(gunEnvironment.getRequiredProperty("db.validationQuery"));

        return gunDataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager gunTransactionManager = new JpaTransactionManager();
        gunTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return gunTransactionManager;
    }

    public Properties getHibernateProperties() {
        try {
            Properties gunProperties = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
            gunProperties.load(is);

            return gunProperties;
        } catch (IOException e) {
            throw new IllegalArgumentException("File 'hibernate.properties' not found!", e);
        }
    }

}
