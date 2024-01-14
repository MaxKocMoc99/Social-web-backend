package KocMoc.social.web.users.myGroups;
import KocMoc.social.web.groups.Group;
import KocMoc.social.web.groups.GroupRepository;
import KocMoc.social.web.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyGroupService {

    @Autowired
    MyGroupRepository repository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public MyGroup createMyGroup(String id, String name, String type, String routingId) {
       MyGroup myGroup = repository.insert(new MyGroup(routingId));
       groupRepository.save(new Group(name, type, routingId));

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().push("myGroups").value(myGroup))
                .first();

        return myGroup;
    }

    public Optional<MyGroup> deleteMyGroup(String id, String groupId) {

        Optional<MyGroup> group = repository.findById(id);

        repository.deleteById(id);

        groupRepository.deleteById(groupId);

        return group;
    }
    public Optional<MyGroup> changePhoto(String id, String photo) {
        Optional<MyGroup> optionalGroup = repository.findById(id);

        mongoTemplate.update(Group.class)
                .matching(Criteria.where("routingId").is(id))
                .apply(new Update().set("background", photo))
                .first();

        return optionalGroup;
    }

    public Optional<MyGroup> changeBackground(String id, String background) {
        Optional<MyGroup> optionalGroup = repository.findById(id);

        mongoTemplate.update(Group.class)
                .matching(Criteria.where("routingId").is(id))
                .apply(new Update().set("background", background))
                .first();

        return optionalGroup;
    }

}
