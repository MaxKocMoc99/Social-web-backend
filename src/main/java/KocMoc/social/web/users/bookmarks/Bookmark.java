package KocMoc.social.web.users.bookmarks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookmarks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bookmark{
    @Id
    private String id;

    private String routingId;

    public Bookmark(String routingId){
        this.routingId = routingId;
    }

}