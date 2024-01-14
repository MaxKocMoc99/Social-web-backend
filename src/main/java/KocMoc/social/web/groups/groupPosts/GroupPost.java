package KocMoc.social.web.groups.groupPosts;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "groupPosts")
@Data
@NoArgsConstructor
public class GroupPost {
    @Id
    private String id;

    private String routingId;

    public GroupPost(String routingId){
        this.routingId = routingId;
    }
}
