package KocMoc.social.web.groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    GroupRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;

    public Group createGroup(String name, String type, String id){
        return repository.save(new Group(name, type, id));
    }

    public List<Group> allGroups(){
        return repository.findAll();
    }
    public Optional<Group> changePhoto(String id, String photo) {
        Optional<Group> optionalGroup = repository.findById(id);
        mongoTemplate.update(Group.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().set("photo", photo))
                .first();
        return optionalGroup;
    }

    public Optional<Group> changeBackground(String id, String background) {
        Optional<Group> optionalGroup = repository.findById(id);
        mongoTemplate.update(Group.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().set("background", background))
                .first();
        return optionalGroup;
    }

    public Optional<Group> getGroupById(String id){
        return repository.findById(id);
    }
}

