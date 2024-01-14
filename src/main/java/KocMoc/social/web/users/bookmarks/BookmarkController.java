package KocMoc.social.web.users.bookmarks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/bookmarks")
@CrossOrigin("http://127.0.0.1:5173")
public class BookmarkController {
    @Autowired
    BookmarkService service;

    @PostMapping("/add")
    public ResponseEntity<Bookmark> addBookmark(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Bookmark>(service.addBookmark(payload.get("id"), payload.get("routingId")), HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Optional<Bookmark>> removeBookmark(@RequestBody Map<String, String> payload){
        return new ResponseEntity<Optional<Bookmark>>(service.removeBookmark(payload.get("id"), payload.get("bookmarkId")), HttpStatus.OK);
    }
}