package KocMoc.social.web.users.messages;
import KocMoc.social.web.users.groupRooms.GroupRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/messages")
@CrossOrigin("http://127.0.0.1:5173")
public class MessageController {
    @Autowired
    MessageService service;

    @PostMapping("/addMessageToRoom")
    public ResponseEntity<Message> addMessage(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Message>(service.addMessageToRoom(payload.get("text"), payload.get("time"), payload.get("date"), payload.get("src"),payload.get("name"), payload.get("routingId"), payload.get("photo"), payload.get("searchId"), payload.get("roomId"), payload.get("roomId2")), HttpStatus.OK);
    }

    @PostMapping("/removeMessageFromRoom")
    public ResponseEntity<Optional<Message>> removeMessage(@RequestBody Map<String, String> payload){
        return new ResponseEntity<Optional<Message>>(service.removeMessageFromRoom(payload.get("id"), payload.get("id2")), HttpStatus.OK);
    }

    @PostMapping("/changeMessageFromRoom")
    public ResponseEntity<Optional<Message>> changeMessage(@RequestBody Map<String, String> payload){
        return new ResponseEntity<Optional<Message>>(service.changeMessageFromRoom(payload.get("id"), payload.get("id2"), payload.get("text")), HttpStatus.OK);
    }

    @PostMapping("/addMessageToGroupRoom")
    public ResponseEntity<List<GroupRoom>> addMessageToGroupRoom(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<List<GroupRoom>>(service.addMessageToGroupRoom(payload.get("text"), payload.get("time"), payload.get("date"), payload.get("src"),payload.get("name"), payload.get("routingId"), payload.get("photo"), payload.get("searchId"), payload.get("groupRoomRoutingId")), HttpStatus.OK);
    }

    @PostMapping("/removeMessageFromGroupRoom")
    public ResponseEntity<List<Message>> removeMessageFromGroupRoom(@RequestBody Map<String, String> payload){
        return new ResponseEntity<List<Message>>(service.removeMessageFromGroupRoom(payload.get("searchId")), HttpStatus.OK);
    }

    @PostMapping("/changeMessageFromGroupRoom")
    public ResponseEntity<List<Message>> changeMessageFromGroupRoom(@RequestBody Map<String, String> payload){
        return new ResponseEntity<List<Message>>(service.changeMessageFromGroupRoom(payload.get("searchId"), payload.get("text")), HttpStatus.OK);
    }

    @PostMapping("/sendPost")
    public ResponseEntity<Message> sendPost(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Message>(service.sendPost(payload.get("text"), payload.get("time"), payload.get("date"), payload.get("src"),payload.get("name"), payload.get("routingId"), payload.get("photo"), payload.get("searchId"), payload.get("roomId"), payload.get("roomId2"), payload.get("postId")), HttpStatus.OK);
    }
}
