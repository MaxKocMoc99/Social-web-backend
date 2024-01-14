package KocMoc.social.web.groups;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends MongoRepository<Group, ObjectId> {
    Optional<Group> findById(String id);

    void deleteById(String id);
}