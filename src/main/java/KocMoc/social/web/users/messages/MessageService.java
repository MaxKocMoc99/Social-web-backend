package KocMoc.social.web.users.messages;
import KocMoc.social.web.posts.Post;
import KocMoc.social.web.users.groupRooms.GroupRoom;
import KocMoc.social.web.users.groupRooms.GroupRoomRepository;
import KocMoc.social.web.users.rooms.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    MessageRepository repository;

    @Autowired
    GroupRoomRepository groupRoomRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public Message addMessageToRoom(String text, String time, String date, String src, String name, String routingId, String photo, String searchId, String roomId, String roomId2){
        Message message = repository.insert(new Message(text, time, date, src, name, routingId, photo, searchId));

        mongoTemplate.update(Room.class)
                .matching(Criteria.where("id").is(roomId))
                .apply(new Update().push("messages").value(message))
                .first();

        mongoTemplate.update(Room.class)
                .matching(Criteria.where("id").is(roomId2))
                .apply(new Update().push("messages").value(message))
                .first();

        return message;
    }

    public Optional<Message> removeMessageFromRoom(String id, String id2) {
        Optional<Message> message = repository.findById(id);

        repository.deleteById(id);
        repository.deleteById(id2);

        return message;

    }
    public Optional<Message> changeMessageFromRoom(String id, String id2, String text) {
        Optional<Message> optionalMessage = repository.findById(id);


        mongoTemplate.update(Message.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().set("text", text))
                .first();

        mongoTemplate.update(Message.class)
                .matching(Criteria.where("id").is(id2))
                .apply(new Update().set("text", text))
                .first();


        return optionalMessage;
    }

    public List<GroupRoom> addMessageToGroupRoom(String text, String time, String date, String src, String name, String routingId, String photo, String searchId, String groupRoomRoutingId){

        List<GroupRoom> groupRooms = groupRoomRepository.findAllByRoutingId(groupRoomRoutingId);

        for (GroupRoom groupRoom : groupRooms){
            Message message = repository.insert(new Message(text, time, date, src, name, routingId, photo, searchId));
            mongoTemplate.update(GroupRoom.class)
                    .matching(Criteria.where("id").is(groupRoom.getId()))
                    .apply(new Update().push("messages").value(message))
                    .first();
        }

        return groupRooms;
    }

    public List<Message> removeMessageFromGroupRoom(String searchId){

        List<Message> messages = repository.findAllBySearchId(searchId);

        for(Message message : messages){
            repository.deleteById(message.getId());
        }

        return messages;
    }

    public List<Message> changeMessageFromGroupRoom(String searchId, String text) {

        List<Message> messages = repository.findAllBySearchId(searchId);

        for(Message message : messages){
            mongoTemplate.update(Message.class)
                    .matching(Criteria.where("id").is(message.getId()))
                    .apply(new Update().set("text", text))
                    .first();
        }

        return messages;
    }

    public Message sendPost(String text, String time, String date, String src, String name, String routingId, String photo, String searchId, String roomId, String roomId2,String postId){
        Message message = repository.insert(new Message(text, time, date, src, name, routingId, photo, searchId));

        mongoTemplate.update(Room.class)
                .matching(Criteria.where("id").is(roomId))
                .apply(new Update().push("messages").value(message))
                .first();

        mongoTemplate.update(Room.class)
                .matching(Criteria.where("id").is(roomId2))
                .apply(new Update().push("messages").value(message))
                .first();

        mongoTemplate.update(Post.class)
                .matching(Criteria.where("id").is(postId))
                .apply(new Update().inc("sends", 1))
                .first();

        return message;
    }

}
