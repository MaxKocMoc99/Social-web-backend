package KocMoc.social.web.groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
@CrossOrigin("http://127.0.0.1:5173")
public class GroupController {
    @Autowired
    GroupService service;

    @Autowired
    GroupRepository repository;

    @GetMapping
    public ResponseEntity<List<Group>> getGroups() {
        return new ResponseEntity<List<Group>>(service.allGroups(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody Map<String, String> payload){
        return new ResponseEntity<Group>(service.createGroup(payload.get("name"), payload.get("type"), payload.get("id")), HttpStatus.CREATED);
    }

    @PostMapping("/changePhoto")
    public ResponseEntity<Optional<Group>> changePhoto(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Optional<Group>>(service.changePhoto(payload.get("id"), payload.get("photo")), HttpStatus.OK);
    }

    @PostMapping("/changeBackground")
    public ResponseEntity<Optional<Group>> changeBackground(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Optional<Group>>(service.changeBackground(payload.get("id"), payload.get("background")), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>  removeGroup(@PathVariable String id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Group>> getGroupById(@PathVariable String id) {
        return new ResponseEntity<Optional<Group>>(service.getGroupById(id), HttpStatus.OK);
    }
}
