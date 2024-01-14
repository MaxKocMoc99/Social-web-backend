package KocMoc.social.web.users.notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/notifications")
@CrossOrigin("http://127.0.0.1:5173")
public class NotificationController {
    @Autowired
    private NotificationService service;

    @PostMapping("/send")
    public ResponseEntity<Notification> sendNotification(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Notification>(service.sendNotification(payload.get("name"), payload.get("surname"), payload.get("date"),payload.get("photo"), payload.get("id"), payload.get("routingId")), HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<Optional<Notification>> cancelNotification(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Optional<Notification>>(service.cancelNotification(payload.get("id")), HttpStatus.OK);
    }

    @PostMapping("/agree")
    public ResponseEntity<Optional<Notification>> agreeNotification(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Optional<Notification>>(service.agreeNotification(payload.get("id"), payload.get("userId"),  payload.get("name"),  payload.get("surname"),  payload.get("photo"),  payload.get("routingId"), payload.get("userId2"),  payload.get("name2"),  payload.get("surname2"),  payload.get("photo2"),  payload.get("routingId2")), HttpStatus.OK);
    }
}
