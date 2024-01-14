package KocMoc.social.web.users.subscribedUsers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "subscribedUsers")
public class SubscribedUser {
    @Id
    private String id;

    private String routingId;

    public SubscribedUser(String routingId){
        this.routingId = routingId;
    }
}

