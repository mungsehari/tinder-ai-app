package com.hari.conversations;

import com.hari.profiles.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class ConversationController {

    private ConversationRepository conversationRepository;
    private ProfileRepository profileRepository;

    public ConversationController(ConversationRepository conversationRepository, ProfileRepository profileRepository) {
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
    }

    @PostMapping("/conversation")
    public  Conversation createNewConversation(@RequestBody CreateConversationRequest request) {
        profileRepository.findById(request.profileId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Unable to find a profile with ID"+request.profileId));
        Conversation conversation=new Conversation(
                UUID.randomUUID().toString(),
                request.profileId(),
                new ArrayList<>()
        );
        conversationRepository.save(conversation);
        return conversation;

    }
    @GetMapping("/conversation/{conversationId}")
    public Conversation getConversation(@PathVariable String conversationId){
       return conversationRepository.findById(conversationId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Unable to find conversation with ID: "+conversationId));


    }


    @PostMapping("/conversation/{conversationId}")
    public Conversation addMessageToConversation(
            @PathVariable String conversationId,
            @RequestBody ChatMessage chatMessage
    ){
      Conversation conversation=  conversationRepository.findById(conversationId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Unable to find conversation with ID: "+conversationId));
        profileRepository.findById(chatMessage.authorId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Unable to find a profile with ID"+chatMessage.authorId()));

      ChatMessage messageWithTime=new ChatMessage(
              chatMessage.messageText(),
              chatMessage.authorId(),
              LocalDateTime.now()
      );
      conversation.messages().add(messageWithTime);
      conversationRepository.save(conversation);
      return conversation;
    }


    public record CreateConversationRequest(String profileId) {}
}
