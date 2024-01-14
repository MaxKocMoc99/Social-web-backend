package KocMoc.social.web.users.comments;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
@Data
@NoArgsConstructor
public class Comment {
    @Id
    private String id;

    private String text;

    private String time;

    private String src;

    private String name;

    private String photo;

    private String routingId;

    public Comment(String name, String text, String time, String src, String photo, String routingId) {
        this.name = name;
        this.text = text;
        this.time = time;
        this.src = src;
        this.photo = photo;
        this.routingId = routingId;
    }
}
