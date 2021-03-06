package com.cayan.contentservice.service.impl;


import com.cayan.common.dto.ScienceContentDTO;
import com.cayan.common.dto.UserDTO;
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

            ScienceContent currentContext = null;
            Person currentUser = null;

            if(foundedPerson.isPresent()) {
                currentUser = foundedPerson.get();
            }
            else {
                //TODO: kullanıcı bulunmazsa yeni oluştur.
                UserDTO user = userService.getUserFromAuthDb(scienceContentDTO.getUserId());
                Person p1 = new Person(user.getId(), user.getUsername(), user.getProfilePicture());
                currentUser = contentService.addNewUser(p1);
            }

                switch (scienceContentDTO.getRelationship()) {

                    case AUTHOR_BY:

                        currentContext  = new ScienceContent(scienceContentDTO.getTitle(),scienceContentDTO.getContent(), scienceContentDTO.getImage(), currentUser.getUserId());

                        if(scienceContentDTO.getId() != null) {
                            currentContext =  contentService.getContent(scienceContentDTO.getId()).get();
                        }

                        AuthorBy sharedBy = new AuthorBy(currentUser, currentContext);
                        currentContext.setAuthorBy(sharedBy);
                        currentContext.setLikeCount(Long.valueOf(0));
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
                            Long beforeCount = scienceContent.getLikeCount();
                            scienceContent.setLikeCount(beforeCount == null ? 0 : beforeCount + 1);
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
