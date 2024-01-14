package KocMoc.social.web.users.myGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/myGroups")
@CrossOrigin("http://127.0.0.1:5173")
public class MyGroupController {
    @Autowired
    MyGroupService service;

    @Autowired
    MyGroupRepository repository;

    @PostMapping("/create")
    public ResponseEntity<MyGroup> createMyGroup(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<MyGroup>(service.createMyGroup(payload.get("id"), payload.get("name"), payload.get("type"), payload.get("routingId")), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Optional<MyGroup>> deleteMyGroup(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Optional<MyGroup>>(service.deleteMyGroup(payload.get("id"), payload.get("groupId")), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>  removeMyGroup(@PathVariable String id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/changePhoto")
    public ResponseEntity<Optional<MyGroup>> changePhoto(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Optional<MyGroup>>(service.changePhoto(payload.get("id"), payload.get("photo")), HttpStatus.OK);
    }

    @PostMapping("/changeBackground")
    public ResponseEntity<Optional<MyGroup>> changeBackground(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Optional<MyGroup>>(service.changeBackground(payload.get("id"), payload.get("background")), HttpStatus.OK);
    }
}
