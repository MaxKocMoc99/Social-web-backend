package KocMoc.social.web.users.rooms;
import KocMoc.social.web.users.messages.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import java.util.List;

@Document(collection = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    private String id;
    @DocumentReference
    private List<Message> messages;

    private String name;

    private String surname;

    private String photo;

    private String routingId;

    public Room(String name, String surname, String photo, String routingId) {
        this.name = name;
        this.surname = surname;
        this.photo = photo;
        this.routingId = routingId;
    }
}