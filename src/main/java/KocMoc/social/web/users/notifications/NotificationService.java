package KocMoc.social.web.users.notifications;
import KocMoc.social.web.users.User;
import KocMoc.social.web.users.friends.Friend;
import KocMoc.social.web.users.friends.FriendRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository repository;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Notification sendNotification(String name, String surname, String date, String photo, String id, String routingId) {
        Notification notification = repository.insert(new Notification(name, surname, date, photo, routingId));

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().push("notifications").value(notification))
                .first();

        return notification;
    }

    public Optional<Notification> cancelNotification(String id) {
        Optional<Notification> optionalNotification = repository.findById(id);

        mongoTemplate.update(Notification.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().set("isCancelled", true))
                .first();

        return optionalNotification;
    }

    public Optional<Notification> agreeNotification(String id, String userId, String name, String surname, String photo, String routingId, String userId2, String name2, String surname2, String photo2, String routingId2) {

        Optional<Notification> optionalNotification = repository.findById(id);

        Friend friend = friendRepository.insert(new Friend(name, surname, photo, routingId));
        Friend friend2 = friendRepository.insert(new Friend(name2, surname2, photo2, routingId2));

        mongoTemplate.update(Notification.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().set("isAgreed", true))
                .first();

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(userId))
                .apply(new Update().push("friends").value(friend))
                .first();

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(userId2))
                .apply(new Update().push("friends").value(friend2))
                .first();

        return optionalNotification;
    }
}
