package KocMoc.social.web.posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
@CrossOrigin("http://127.0.0.1:5173")
public class PostController {
    @Autowired
    PostService service;

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        return new ResponseEntity<List<Post>>(service.allPosts(), HttpStatus.OK);
    }

    @PostMapping("/addUserPost")
    public ResponseEntity<Post> addUserPost(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Post>(service.addUserPost(payload.get("name"), payload.get("time"), payload.get("text"), payload.get("img"), payload.get("circleImage"), payload.get("routingId"), payload.get("id")), HttpStatus.OK);
    }

    @PostMapping("/addGroupPost")
    public ResponseEntity<Post> addGroupPost(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Post>(service.addGroupPost(payload.get("text"), payload.get("time"), payload.get("img"), payload.get("name"), payload.get("circleImage"), payload.get("id"), payload.get("routingId"), payload.get("userId")), HttpStatus.OK);
    }
}
