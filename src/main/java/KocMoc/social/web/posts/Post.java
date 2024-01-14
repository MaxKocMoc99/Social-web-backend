package KocMoc.social.web.posts;

import KocMoc.social.web.users.comments.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    private String id;

    private String text;

    private String time;

    private String img;

    private Integer likes;
    @DocumentReference
    private List<Comment> comments;

    private Integer sends;

    private String name;

    private String circleImage;

    private String routingId;

    public Post(String name, String time, String text, String img, String circleImage, String routingId){
        this.name = name;
        this.time = time;
        this.text = text;
        this.img = img;
        this.circleImage = circleImage;
        this.routingId = routingId;
    }

}
