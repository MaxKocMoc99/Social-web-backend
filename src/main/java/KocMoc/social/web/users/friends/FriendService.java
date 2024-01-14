package KocMoc.social.web.users.friends;
import KocMoc.social.web.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendService {
    @Autowired
    private FriendRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Friend addFriend(String name, String surname, String photo, String id, String routingId, String name2, String surname2, String photo2, String id2, String routingId2){
        Friend friend = repository.insert(new Friend(name, surname, photo, routingId));
        Friend friend2 = repository.insert(new Friend(name2, surname2, photo2, routingId2));

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().push("friends").value(friend))
                .first();

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(id2))
                .apply(new Update().push("friends").value(friend2))
                .first();

        return friend;
    }

    public Optional<Friend> removeFriend(String id, String id2){

        Optional<Friend> friend = repository.findById(id);

        repository.deleteById(id);
        repository.deleteById(id2);


        return friend; }


}
