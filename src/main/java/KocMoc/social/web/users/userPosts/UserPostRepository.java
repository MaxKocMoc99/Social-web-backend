package KocMoc.social.web.users.userPosts;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostRepository extends MongoRepository<UserPost, ObjectId> {
}
