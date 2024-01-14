package KocMoc.social.web.groups;
import KocMoc.social.web.groups.groupPosts.GroupPost;
import KocMoc.social.web.groups.users.GroupUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "groups")
public class Group {
    @Id
    private String id;

    private String type;

    private String name;

    @DocumentReference
    private List<GroupUser> users;

    private String photo;

    private String background;
    @DocumentReference
    private List<GroupPost> groupPosts;

    public Group(String name, String type, String id){
        this.name = name;
        this.type = type;
        this.id = id;
    }
}