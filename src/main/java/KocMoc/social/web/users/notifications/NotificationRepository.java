package KocMoc.social.web.users.notifications;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, ObjectId> {
    Optional<Notification> findById(String id);
}
