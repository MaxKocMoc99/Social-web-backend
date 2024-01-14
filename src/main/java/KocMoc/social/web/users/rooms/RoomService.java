package KocMoc.social.web.users.rooms;
import KocMoc.social.web.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    RoomRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Room createRoom(String name, String surname, String photo, String routingId, String userId, String name2, String surname2, String photo2, String routingId2, String userId2){
        Room room1 = repository.insert(new Room(name, surname, photo, routingId));
        Room room2 = repository.insert(new Room(name2, surname2, photo2, routingId2));

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(userId))
                .apply(new Update().push("rooms").value(room1))
                .first();

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(userId2))
                .apply(new Update().push("rooms").value(room2))
                .first();

        return room1;
    }
}
