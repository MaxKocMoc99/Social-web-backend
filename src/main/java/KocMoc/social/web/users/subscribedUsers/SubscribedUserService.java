package KocMoc.social.web.users.subscribedUsers;
import KocMoc.social.web.users.User;
import KocMoc.social.web.users.notifications.Notification;
import KocMoc.social.web.users.notifications.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscribedUserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    SubscribedUserRepository repository;

    @Autowired
    NotificationRepository notificationRepository;

    public SubscribedUser addNewSubscribedUser(String userId, String routingId, String userId2, String name, String surname, String date, String photo, String routingId2){
        SubscribedUser subscribedUser= repository.insert(new SubscribedUser(routingId));

        Notification notification = notificationRepository.insert(new Notification(name, surname, date, photo, routingId2));

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(userId2))
                .apply(new Update().push("notifications").value(notification))
                .first();

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(userId))
                .apply(new Update().push("subscribedUsers").value(subscribedUser))
                .first();

        return  subscribedUser;
    }

    public Optional<SubscribedUser> removeSubscribedUser(String id){
        Optional<SubscribedUser> subscribedUser = repository.findById(id);

        repository.deleteById(id);


        return subscribedUser;
    }

}
