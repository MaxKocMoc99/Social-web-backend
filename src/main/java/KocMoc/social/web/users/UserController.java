package KocMoc.social.web.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://127.0.0.1:5173")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<List<User>>(service.allUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable String id) {
        return new ResponseEntity<Optional<User>>(service.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody Map<String, String> payload){
        return new ResponseEntity<User>(service.createUser(payload.get("id"), payload.get("name"), payload.get("surname"), payload.get("city"), payload.get("birth")), HttpStatus.CREATED);
    }

    @PostMapping("/changePhoto")
    public ResponseEntity<Optional<User>> changePhoto(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Optional<User>>(service.changePhoto(payload.get("id"), payload.get("photo")), HttpStatus.OK);
    }

    @PostMapping("/changeBackground")
    public ResponseEntity<Optional<User>> changeBackground(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Optional<User>>(service.changeBackground(payload.get("id"), payload.get("background")), HttpStatus.OK);
    }


}