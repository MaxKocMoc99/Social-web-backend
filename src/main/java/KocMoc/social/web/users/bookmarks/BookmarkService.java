package KocMoc.social.web.users.bookmarks;
import KocMoc.social.web.posts.Post;
import KocMoc.social.web.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookmarkService {
    @Autowired
    BookmarkRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;

    public Bookmark addBookmark(String id, String routingId){
        Bookmark bookmark = repository.insert(new Bookmark(routingId));

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().push("bookmarks").value(bookmark))
                .first();

        mongoTemplate.update(Post.class)
                .matching(Criteria.where("id").is(routingId))
                .apply(new Update().inc("likes", 1))
                .first();

        return bookmark;
    }

    public Optional<Bookmark> removeBookmark(String id, String bookmarkId){

        Optional<Bookmark> bookmark = repository.findById(bookmarkId);

        repository.deleteById(id);

        mongoTemplate.update(Post.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().inc("likes", -1))
                .first();

        return bookmark;
    }
}
