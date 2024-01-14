package KocMoc.social.web.users.gallery;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gallery")
@Data
@NoArgsConstructor
public class Photo {
    @Id
    private String src;

    private String id;

    public Photo(String src){
        this.src = src;
    }
}
