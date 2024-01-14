package KocMoc.social.web.users.bookmarks;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookmarkRepository extends MongoRepository<Bookmark, ObjectId> {
    void deleteById(String id);

    Optional<Bookmark> findById(String id);
}
