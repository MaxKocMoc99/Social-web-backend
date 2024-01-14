package KocMoc.social.web.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public User createUser(String id, String name, String surname, String city, String birth){
        return repository.save(new User(id, name, surname, city, birth));
    }

    public List<User> allUsers(){
        return repository.findAll();
    }

    public Optional<User> getUserById(String id){
        return repository.findById(id);
    }

    public Optional<User> changePhoto(String id, String photo) {
        Optional<User> optionalUser = repository.findById(id);
        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().set("photo", photo))
                .first();
        return optionalUser;
    }

    public Optional<User> changeBackground(String id, String background) {
        Optional<User> optionalUser = repository.findById(id);
        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().set("background", background))
                .first();
        return optionalUser;
    }

}
