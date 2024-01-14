package KocMoc.social.web.users.subscribedGroups;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscribedGroupRepository extends MongoRepository<SubscribedGroup, ObjectId> {
    Optional<SubscribedGroup> findById(String id);

    void deleteById(String id);
}
