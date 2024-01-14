package KocMoc.social.web.users.comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/comments")
@CrossOrigin("http://127.0.0.1:5173")
public class CommentController {

    @Autowired
    CommentService service;

    @PostMapping("/addPostComment")
    public ResponseEntity<Comment> addPostComment(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Comment>(service.addPostComment(payload.get("name"), payload.get("text"), payload.get("time"), payload.get("src"), payload.get("postId"), payload.get("photo"), payload.get("routingId")), HttpStatus.OK);
    }
}
