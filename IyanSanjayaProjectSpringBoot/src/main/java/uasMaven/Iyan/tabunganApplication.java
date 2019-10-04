package uasMaven.Iyan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class tabunganApplication {
	public static void main(String[] args) {
		SpringApplication.run(tabunganApplication.class, args);
	}

}

