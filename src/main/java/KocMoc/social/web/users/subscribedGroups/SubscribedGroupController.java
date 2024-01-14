package KocMoc.social.web.users.subscribedGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/subscribedGroups")
@CrossOrigin("http://127.0.0.1:5173")
public class SubscribedGroupController {
    @Autowired
    SubscribedGroupService service;

    @Autowired
    SubscribedGroupRepository repository;

    @PostMapping("/subscribeGroup")
    public ResponseEntity<SubscribedGroup> subscribeOnGroup(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<SubscribedGroup>(service.subscribeGroup(payload.get("id"), payload.get("name"), payload.get("type"), payload.get("routingId"), payload.get("photo"), payload.get("subscribers")), HttpStatus.OK);
    }

    @PostMapping("/unsubscribeGroup")
    public ResponseEntity<Optional<SubscribedGroup>> unsubscribeOnGroup(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Optional<SubscribedGroup>>(service.unsubscribeGroup(payload.get("id"), payload.get("userId")), HttpStatus.OK);
    }

}
