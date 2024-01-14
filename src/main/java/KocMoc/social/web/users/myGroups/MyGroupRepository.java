package KocMoc.social.web.users.myGroups;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyGroupRepository extends MongoRepository<MyGroup, ObjectId> {
    void deleteById(String id);

    Optional<MyGroup> findById(String id);
}
