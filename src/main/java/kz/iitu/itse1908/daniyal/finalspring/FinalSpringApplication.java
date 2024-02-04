package kz.iitu.itse1908.daniyal.finalspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EntityScan("kz.iitu.itse1908.daniyal.finalspring.models")
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@Component
@EnableAspectJAutoProxy
@EnableScheduling
@EnableJms
public class FinalSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalSpringApplication.class, args);
    }

}
