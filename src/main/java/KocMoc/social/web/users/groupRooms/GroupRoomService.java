package KocMoc.social.web.users.groupRooms;
import KocMoc.social.web.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupRoomService {

    @Autowired GroupRoomRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public String createGroupRoom(String name, String photo, String routingId, List<String> usersIds) {

        for (String userId : usersIds) {
            GroupRoom groupRoom = repository.insert(new GroupRoom(name, photo, routingId, usersIds));
            mongoTemplate.update(User.class)
                    .matching(Criteria.where("id").is(userId))
                    .apply(new Update().push("groupRooms").value(groupRoom))
                    .first();
        }

        return photo + name + usersIds.get(0);
    }

    public List<GroupRoom> addNewUsersToGroupRoom(List<String> usersIds, List<String> groupRoomUsersIds, String routingId, String name, String photo){

        List<GroupRoom> groupRooms = repository.findAllByRoutingId(routingId);
        List<String> allUsers = new ArrayList<String>();

        allUsers.addAll(groupRoomUsersIds);
        allUsers.addAll(usersIds);

        for (GroupRoom groupRoom : groupRooms){
            mongoTemplate.update(GroupRoom.class)
                    .matching(Criteria.where("id").is(groupRoom.getId()))
                    .apply(new Update().push("users").value(usersIds))
                    .first();
        }

        for (String userId : usersIds){
            GroupRoom groupRoom = repository.insert(new GroupRoom(name, photo, routingId, allUsers));
            mongoTemplate.update(User.class)
                    .matching(Criteria.where("id").is(userId))
                    .apply(new Update().push("groupRooms").value(groupRoom))
                    .first();
        }

        return groupRooms;
    }

    public List<GroupRoom> addNewUserToGroupRoom(String userId, String routingId, String name, String photo, List<String> groupRoomUsersIds){

        List<GroupRoom> groupRooms = repository.findAllByRoutingId(routingId);
        List<String> allUsers = new ArrayList<String>();

        allUsers.addAll(groupRoomUsersIds);
        allUsers.add(userId);

        for (GroupRoom groupRoom : groupRooms){
            mongoTemplate.update(GroupRoom.class)
                    .matching(Criteria.where("id").is(groupRoom.getId()))
                    .apply(new Update().push("users").value(userId))
                    .first();
        }

            GroupRoom groupRoom = repository.insert(new GroupRoom(name, photo, routingId, allUsers));
            mongoTemplate.update(User.class)
                    .matching(Criteria.where("id").is(userId))
                    .apply(new Update().push("groupRooms").value(groupRoom))
                    .first();


        return groupRooms;
    }

    public Optional<GroupRoom> deleteGroupRoom(String id, String routingId, String userId){
        Optional<GroupRoom> groupRoom = repository.findById(id);

        repository.deleteById(id);

        List<GroupRoom> groupRooms = repository.findAllByRoutingId(routingId);

        for (GroupRoom grRoom : groupRooms){
            mongoTemplate.update(GroupRoom.class)
                    .matching(Criteria.where("id").is(grRoom.getId()))
                    .apply(new Update().pull("users", userId))
                    .first();
        }

        return groupRoom;
    }

    public List<GroupRoom> changePhoto(String id, String photo) {
        List<GroupRoom> groupRooms = repository.findAllByRoutingId(id);
        for ( GroupRoom groupRoom : groupRooms) {
            mongoTemplate.update(GroupRoom.class)
                    .matching(Criteria.where("id").is(groupRoom.getId()))
                    .apply(new Update().set("photo", photo))
                    .first();
        }

        return groupRooms;
    }

}
