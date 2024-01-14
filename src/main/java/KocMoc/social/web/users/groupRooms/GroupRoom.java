package KocMoc.social.web.users.groupRooms;
import KocMoc.social.web.users.messages.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "groupRooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupRoom {
    @Id
    private String id;
    @DocumentReference
    private List<Message> messages;

    private List<String> users;

    private String name;

    private String photo;

    private String routingId;

    public GroupRoom(String name, String photo, String routingId, List<String> users){
        this.name = name;
        this.photo = photo;
        this.routingId = routingId;
        this.users = users;
    }
}
