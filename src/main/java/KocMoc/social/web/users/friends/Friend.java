package KocMoc.social.web.users.friends;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "friends")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    @Id
    private String id;

    private String name;

    private String surname;

    private String photo;

    private String routingId;

   public Friend(String name, String surname, String photo, String routingId){
       this.name = name;
       this.surname = surname;
       this.photo = photo;
       this.routingId = routingId;
   }
}
