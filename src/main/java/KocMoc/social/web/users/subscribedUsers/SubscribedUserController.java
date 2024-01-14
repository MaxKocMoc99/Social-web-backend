package KocMoc.social.web.users.subscribedUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/subscribedUsers")
@CrossOrigin("http://127.0.0.1:5173")
public class SubscribedUserController {
    @Autowired
    SubscribedUserService service;

    @PostMapping("/createNewSubscribedUser")
    public ResponseEntity<SubscribedUser> createNewSubscribedUser(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<SubscribedUser>(service.addNewSubscribedUser(payload.get("userId"), payload.get("routingId"), payload.get("userId2"), payload.get("name"), payload.get("surname"), payload.get("date"), payload.get("photo"), payload.get("routingId2")), HttpStatus.OK);
    }

    @PostMapping("/removeSubscribedUser")
    public ResponseEntity<Optional<SubscribedUser>>removeSubscribedUser(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Optional<SubscribedUser>>(service.removeSubscribedUser(payload.get("id")), HttpStatus.OK);
    }
}
