package KocMoc.social.web.users.subscribedUsers;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SubscribedUserRepository extends MongoRepository<SubscribedUser, ObjectId> {
    Optional<SubscribedUser> findById(String id);

    void deleteById(String id);
}
