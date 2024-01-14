package KocMoc.social.web.users.friends;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendRepository extends MongoRepository<Friend, ObjectId> {
    void deleteById(String id);

    Optional<Friend> findById(String id);
}
