package KocMoc.social.web.groups.groupPosts;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPostRepository extends MongoRepository<GroupPost, ObjectId> {
}
