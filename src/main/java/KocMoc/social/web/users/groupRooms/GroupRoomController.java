package KocMoc.social.web.users.groupRooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/groupRooms")
@CrossOrigin("http://127.0.0.1:5173")
public class GroupRoomController {

    @Autowired
    GroupRoomService service;

    @PostMapping("/createNewGroupRoom")
    public ResponseEntity<String> createNewGroupRoom(@RequestBody Map<String, Object> payload) {
        return new ResponseEntity<String>(service.createGroupRoom((String) payload.get("name"), (String) payload.get("photo"), (String) payload.get("routingId"), (List<String>) payload.get("usersIds")), HttpStatus.OK);
    }

    @PostMapping("/addNewUsersToGroupRoom")
    public ResponseEntity<List<GroupRoom>> addNewUsersToGroupRoom(@RequestBody Map<String, Object> payload) {
        return new ResponseEntity<List<GroupRoom>>(service.addNewUsersToGroupRoom((List<String>) payload.get("usersIds"), (List<String>) payload.get("groupRoomUsersIds"), (String) payload.get("routingId"), (String) payload.get("name"), (String) payload.get("photo")), HttpStatus.OK);
    }

    @PostMapping("/addNewUserToGroupRoom")
    public ResponseEntity<List<GroupRoom>> addNewUserToGroupRoom(@RequestBody Map<String, Object> payload) {
        return new ResponseEntity<List<GroupRoom>>(service.addNewUserToGroupRoom((String) payload.get("userId"), (String) payload.get("routingId"), (String) payload.get("name"), (String) payload.get("photo"), (List<String>) payload.get("groupRoomUsersIds")), HttpStatus.OK);
    }

    @PostMapping("/deleteGroupRoom")
    public ResponseEntity<Optional<GroupRoom>> deleteGroupRoom(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Optional<GroupRoom>>(service.deleteGroupRoom(payload.get("id"), payload.get("routingId"), payload.get("userId")), HttpStatus.OK);
    }


    @PostMapping("/changeGroupRoomPhoto")
    public ResponseEntity<List<GroupRoom>> changeGroupRoomPhoto(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<List<GroupRoom>>(service.changePhoto(payload.get("id"), payload.get("photo")), HttpStatus.OK);
    }

}
