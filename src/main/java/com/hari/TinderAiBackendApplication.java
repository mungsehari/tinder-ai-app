package com.hari;

import com.hari.profiles.Gender;
import com.hari.profiles.Profile;
import com.hari.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository profileRepository;

	public static void main(String[] args) {
		SpringApplication.run(TinderAiBackendApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Profile profile=new Profile(
				"1",
				"Hariprasad ",
				"Mungase",
				21,
				"Indian",
				Gender.MALE,
				"Full stacke Java dev",
				"food.jpg",
				"INIP"


		);
		profileRepository.save(profile);
		profileRepository.findAll().forEach(System.out::println);
	}
}
