package KocMoc.social.web.users.messages;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends MongoRepository<Message, ObjectId> {
    Optional<Message> findById(String id);

    void deleteById(String id);

    List<Message> findAllBySearchId(String searchId);
}
