package KocMoc.social.web.users.rooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/rooms")
@CrossOrigin("http://127.0.0.1:5173")
public class RoomController {

    @Autowired
    RoomService service;

    @PostMapping("/createNewRoom")
    public ResponseEntity<Room> createNewRoom(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Room>(service.createRoom(payload.get("name"), payload.get("surname") ,payload.get("photo"), payload.get("routingId"), payload.get("userId"), payload.get("name2"), payload.get("surname2"), payload.get("photo2"), payload.get("routingId2"), payload.get("userId2")), HttpStatus.OK);
    }
}
