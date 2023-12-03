package ir.bigz.springboot.springjdbc;

import ir.bigz.springboot.springjdbc.post.Post;
import ir.bigz.springboot.springjdbc.post.PostService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class SpringJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(@Qualifier("jdbcClientPostService") PostService postService) {
		return args -> {
			postService.create(new Post("1234", "Hello World", "hello-world", LocalDate.now(), 1, "java, spring"));
		};
	}
}
