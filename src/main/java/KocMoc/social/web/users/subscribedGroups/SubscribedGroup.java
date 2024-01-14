package KocMoc.social.web.users.subscribedGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "subscribedGroups")
public class SubscribedGroup {
    @Id
    private String id;

    private String type;

    private String name;

    private String photo;

    private Integer subscribers;

    private String routingId;

    public SubscribedGroup(String name, String type, String routingId, String photo, String subscribers){
        this.name = name;
        this.type = type;
        this.routingId = routingId;
        this.photo = photo;
        this.subscribers = Integer.valueOf(subscribers);
    }
}
