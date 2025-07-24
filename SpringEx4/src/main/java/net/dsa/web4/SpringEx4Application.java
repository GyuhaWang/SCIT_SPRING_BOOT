package net.dsa.web4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
 * JPA audition 기능 활성화
 *  - 해당 기능이 활성화 되어 있어야 @createDate, @LastModifiedDate 같은 Annotation이 동작함
 * */
@EnableJpaAuditing
@SpringBootApplication
public class SpringEx4Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringEx4Application.class, args);
	}

}
