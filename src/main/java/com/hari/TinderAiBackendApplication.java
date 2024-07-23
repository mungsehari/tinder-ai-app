package com.hari;

import com.hari.conversations.ChatMessage;
import com.hari.conversations.Conversation;
import com.hari.conversations.ConversationRepository;
import com.hari.profiles.Gender;
import com.hari.profiles.Profile;
import com.hari.profiles.ProfileCreationService;
import com.hari.profiles.ProfileRepository;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileCreationService profileCreationService;

	public static void main(String[] args) {
		SpringApplication.run(TinderAiBackendApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		profileCreationService.saveProfilesToDB();

	}

}
