package KocMoc.social.web.users.myGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "myGroups")
public class MyGroup {
    @Id
    private String id;

    private String routingId;

    public MyGroup(String routingId){
        this.routingId = routingId;
    }
}
