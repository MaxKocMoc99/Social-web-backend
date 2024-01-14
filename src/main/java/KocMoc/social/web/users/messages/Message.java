package KocMoc.social.web.users.messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    private String id;

    private String text;

    private String time;

    private String date;

    private String src;

    private String name;

    private String routingId;

    private String photo;

    private String searchId;

    public Message(String text, String time, String date, String src, String name, String routingId, String photo, String searchId){
        this.text = text;
        this.time = time;
        this.date = date;
        this.src = src;
        this.name = name;
        this.routingId = routingId;
        this.photo = photo;
        this.searchId = searchId;
    }

}
