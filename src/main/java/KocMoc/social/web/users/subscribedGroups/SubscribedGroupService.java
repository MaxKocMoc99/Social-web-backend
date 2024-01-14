package KocMoc.social.web.users.subscribedGroups;
import KocMoc.social.web.groups.Group;
import KocMoc.social.web.groups.users.GroupUser;
import KocMoc.social.web.groups.users.GroupUserRepository;
import KocMoc.social.web.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscribedGroupService {
   @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    SubscribedGroupRepository repository;

    @Autowired
    GroupUserRepository groupUserRepository;

    public SubscribedGroup subscribeGroup(String id, String name, String type, String routingId, String photo, String subscribers) {
        SubscribedGroup group = repository.insert(new SubscribedGroup(name, type, routingId, photo, subscribers));
        GroupUser groupUser = groupUserRepository.insert(new GroupUser(id));

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().push("subscribedGroups").value(group))
                .first();

        mongoTemplate.update(Group.class)
                .matching(Criteria.where("id").is(routingId))
                .apply(new Update().push("groupUsers").value(groupUser))
                .first();

        return group;
    }

    public Optional<SubscribedGroup> unsubscribeGroup(String id, String userId) {
        Optional<SubscribedGroup> subscribedGroup = repository.findById(id);

        repository.deleteById(id);

        groupUserRepository.deleteById(userId);

        return subscribedGroup;
    }

}
