package com.cayan.contentservice.service.impl;


import com.cayan.common.dto.ScienceContentDTO;
import com.cayan.contentservice.model.LikedBy;
import com.cayan.contentservice.model.Person;
import com.cayan.contentservice.model.AuthorBy;
import com.cayan.contentservice.model.ScienceContent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KafkaConsumerService {

    @Autowired
    ContentService contentService;

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topic}")
    public void listenKafka(@Payload String content) throws JsonProcessingException {

        try {

            ScienceContentDTO scienceContentDTO = objectMapper.readValue(content, ScienceContentDTO.class);
            Optional<Person>  foundedPerson = contentService.getPersonByUserId(scienceContentDTO.getUserId());

            Person currentUser = null;
            ScienceContent currentContext = null;

            if(foundedPerson.isPresent()) {
                currentUser = foundedPerson.get();
            }
            else {
                currentUser  = new Person(scienceContentDTO.getUserId());
            }

                switch (scienceContentDTO.getRelationship()) {

                    case AUTHOR_BY:

                        currentContext  = new ScienceContent(scienceContentDTO.getContent(), scienceContentDTO.getTitle());

                        //TODO: Id ve userId check yapılacak.
                        if(scienceContentDTO.getId() != null) {
                            currentContext =  contentService.getContent(scienceContentDTO.getId()).get();
                        }

                        AuthorBy sharedBy = new AuthorBy(currentUser, currentContext);
                        currentContext.setAuthorBy(sharedBy);

                        currentContext.setCreatedAt(LocalDateTime.now());
                        contentService.save(currentContext);

                        break;
                    case LIKED_BY:

                        Optional<ScienceContent>  foundedContent =  contentService.getContent(scienceContentDTO.getId());

                        if(foundedContent.isPresent()) {

                            ScienceContent scienceContent = foundedContent.get();

                            LikedBy likedBy = new LikedBy(currentUser, scienceContent);
                            List<LikedBy> likedByList = new ArrayList<>();
                            likedByList.add(likedBy);
                            scienceContent.setLikedByList(likedByList);

                            contentService.save(scienceContent);
                        }
                        break;
                    case SHARED_BY:
                        break;
                    default:
                        break;
                }

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
