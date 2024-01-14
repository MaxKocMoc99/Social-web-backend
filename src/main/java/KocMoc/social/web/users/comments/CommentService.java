package KocMoc.social.web.users.comments;
import KocMoc.social.web.posts.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;

    public Comment addPostComment(String name, String text, String time, String src, String postId, String photo, String routingId){
        Comment comment = repository.insert(new Comment(name, text, time, src, photo, routingId));

        mongoTemplate.update(Post.class)
                .matching(Criteria.where("id").is(postId))
                .apply(new Update().push("comments").value(comment))
                .first();

        return comment;
    }
}
