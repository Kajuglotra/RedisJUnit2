package org.gfg.junitmock.demo;

import org.gfg.junitmock.demo.Repository.MyUserRepository;
import org.gfg.junitmock.demo.Repository.UserRepository;
import org.gfg.junitmock.demo.model.MyUser;
import org.gfg.junitmock.demo.model.User;
import org.gfg.junitmock.demo.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private MyUserRepository myUserRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MyUser myUser = MyUser.builder().
				username("rahul").
				password(passwordEncoder.encode("rahul123")).
				authorities("qa").
				build();

		MyUser myUser2 = MyUser.builder().
				username("abhishek").
				password(passwordEncoder.encode("abhishek123")).
				authorities("dev").
				build();

		User user = User.builder().
				name("rahul").
				email("rahul@gmail.com").
				password(passwordEncoder.encode("rahul123")).
				enabled(true).
				authorities("qa").
				build();

		User user2 = User.builder().
				name("abhishek").
				email("abhishek@gmail.com").
				password(passwordEncoder.encode("abhishek123")).
				enabled(true).
				authorities("dev").
				build();

		myUserRepository.saveAll(Arrays.asList(myUser, myUser2));
		userRepository.saveAll(Arrays.asList(user, user2));



	}
}
