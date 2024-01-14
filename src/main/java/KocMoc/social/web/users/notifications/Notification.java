package KocMoc.social.web.users.notifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    private String id;

    private String name;

    private String surname;

    private String date;

    private String photo;

    private Boolean isAgreed = false;

    private Boolean isCancelled = false;

    private String routingId;

    public Notification(String name, String surname, String date, String photo, String routingId){
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.photo = photo;
        this.routingId = routingId;
    }
}