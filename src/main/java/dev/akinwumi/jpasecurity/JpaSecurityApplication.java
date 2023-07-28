package dev.akinwumi.jpasecurity;

import dev.akinwumi.jpasecurity.model.Post;
import dev.akinwumi.jpasecurity.model.User;
import dev.akinwumi.jpasecurity.repository.PostRepository;
import dev.akinwumi.jpasecurity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JpaSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaSecurityApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(PostRepository posts, UserRepository  user, PasswordEncoder encoder){
		return args -> {

			user.save(new User(
					"akinwumi",
					encoder.encode("pass"),
					"USER"
			));

			posts.save(new Post(
				"Hello world",
				"hello-worlfd",
				"welcome",
				"Iyanu"
			));
		};
	}
}
