package KocMoc.social.web.users.userPosts;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userPosts")
@Data
@NoArgsConstructor
public class UserPost {
    @Id
    private String id;

    private String routingId;

    public UserPost(String routingId){
       this.routingId = routingId;
    }
}

