package KocMoc.social.web.groups.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "groupsUsers")
public class GroupUser {
    @Id
    private String id;

    private String routingId;

    public GroupUser(String routingId){
        this.routingId = routingId;
    }
}
