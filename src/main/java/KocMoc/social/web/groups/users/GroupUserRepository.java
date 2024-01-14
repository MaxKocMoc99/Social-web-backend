package KocMoc.social.web.groups.users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUserRepository extends MongoRepository<GroupUser, ObjectId> {
    void deleteById(String userId);
}
