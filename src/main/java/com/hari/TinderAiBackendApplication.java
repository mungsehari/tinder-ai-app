package com.hari;

import com.hari.conversations.ChatMessage;
import com.hari.conversations.Conversation;
import com.hari.conversations.ConversationRepository;
import com.hari.profiles.Gender;
import com.hari.profiles.Profile;
import com.hari.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ConversationRepository conversationRepository;

	public static void main(String[] args) {
		SpringApplication.run(TinderAiBackendApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		profileRepository.deleteAll();
		conversationRepository.deleteAll();
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

		 profile=new Profile(
				"2",
				"Narayan ",
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


		Conversation conversation=new Conversation(
				"1",
				profile.id(),
				List.of(
						new ChatMessage("Hi",profile.id(), LocalDateTime.now())
				)
		);

		conversationRepository.save(conversation);
		conversationRepository.findAll().forEach(System.out::println);
	}

}
