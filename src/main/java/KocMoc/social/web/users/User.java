package KocMoc.social.web.users;
import KocMoc.social.web.posts.Post;
import KocMoc.social.web.users.bookmarks.Bookmark;
import KocMoc.social.web.users.friends.Friend;
import KocMoc.social.web.users.gallery.Photo;
import KocMoc.social.web.users.groupRooms.GroupRoom;
import KocMoc.social.web.users.myGroups.MyGroup;
import KocMoc.social.web.users.notifications.Notification;
import KocMoc.social.web.users.rooms.Room;
import KocMoc.social.web.users.subscribedGroups.SubscribedGroup;
import KocMoc.social.web.users.subscribedUsers.SubscribedUser;
import KocMoc.social.web.users.userPosts.UserPost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    private String name;

    private String surname;

    private String city;

    private String birth;

    private String photo;

    private String background;

    private String chatId;

    @DocumentReference
    private List<Photo> gallery;

    @DocumentReference
    private List<Friend> friends;

    @DocumentReference
    private List<SubscribedUser> subscribedUsers;

    @DocumentReference
    private List<Bookmark> bookmarks;

    @DocumentReference
    private List<Notification> notifications;

    @DocumentReference
    private List<Room> rooms;

    @DocumentReference
    private List<GroupRoom> groupRooms;

    @DocumentReference
    private List<MyGroup> myGroups;

    @DocumentReference
    private List<UserPost> userPosts;

    @DocumentReference
    private List<SubscribedGroup> subscribedGroups;

    @DocumentReference
    private List<Post> news;

    public User(String id, String name, String surname, String city, String birth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.birth = birth;
    }
}
