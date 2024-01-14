package KocMoc.social.web.users.friends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/friends")
@CrossOrigin("http://127.0.0.1:5173")
public class FriendController {
    @Autowired
    private FriendService service;

    @PostMapping("/add")
    public ResponseEntity<Friend> addFriend(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Friend>(service.addFriend(payload.get("name"), payload.get("surname"), payload.get("photo"), payload.get("id"), payload.get("routingId"), payload.get("name2"), payload.get("surname2"), payload.get("photo2"), payload.get("id2"), payload.get("routingId2")), HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Optional<Friend>> removeFriend(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Optional<Friend>>(service.removeFriend(payload.get("id"), payload.get("id2")), HttpStatus.OK);
    }
}
