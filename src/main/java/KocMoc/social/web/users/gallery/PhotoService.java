package KocMoc.social.web.users.gallery;

import KocMoc.social.web.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Photo newPhoto(String src, String id){
       Photo photo = repository.insert(new Photo(src));

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().push("gallery").value(photo))
                .first();

        return photo;
    }
}
