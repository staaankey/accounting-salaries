package staaankey.group.accountingsalaries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaRepositories("staaankey.group")
@EntityScan("staaankey.group.*")
public class AccountingSalariesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountingSalariesApplication.class, args);
    }

}
