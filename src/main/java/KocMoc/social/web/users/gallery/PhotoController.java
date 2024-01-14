package KocMoc.social.web.users.gallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/gallery")
@CrossOrigin("http://127.0.0.1:5173")
public class PhotoController {

    @Autowired
    PhotoService service;

    @PostMapping("/newPhoto")
    public ResponseEntity<Photo> newPhoto(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Photo>(service.newPhoto(payload.get("src"), payload.get("id")), HttpStatus.OK);
    }
}
