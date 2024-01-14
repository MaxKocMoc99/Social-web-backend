package KocMoc.social.web.posts;
import KocMoc.social.web.groups.Group;
import KocMoc.social.web.groups.GroupRepository;
import KocMoc.social.web.groups.groupPosts.GroupPost;
import KocMoc.social.web.groups.groupPosts.GroupPostRepository;
import KocMoc.social.web.groups.users.GroupUser;
import KocMoc.social.web.users.User;
import KocMoc.social.web.users.UserRepository;
import KocMoc.social.web.users.friends.Friend;
import KocMoc.social.web.users.userPosts.UserPost;
import KocMoc.social.web.users.userPosts.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository repository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserPostRepository userPostRepository;

    @Autowired
    GroupPostRepository groupPostRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Post> allPosts(){
        return repository.findAll();
    }

    public Post addUserPost(String name, String time,String text, String img, String circleImage, String routingId, String id){
       Post post = repository.save(new Post(text, time, img, name, circleImage, routingId));
       UserPost userPost = userPostRepository.insert(new UserPost(routingId));
       Optional<User> user = userRepository.findById(id);
       List<Friend> friends = user.get().getFriends();

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().push("userPosts").value(userPost))
                .first();

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().push("news").value(post))
                .first();

        for (Friend friend : friends) {
            mongoTemplate.update(User.class)
                    .matching(Criteria.where("id").is(friend.getRoutingId()))
                    .apply(new Update().push("news").value(post))
                    .first();
        }

        return post;
    }

    public Post addGroupPost(String name, String time,String text, String img, String circleImage, String id, String routingId, String userId){
        Post post = repository.save(new Post(text, time, img, name, circleImage, routingId));
        GroupPost groupPost = groupPostRepository.insert(new GroupPost(routingId));
        Optional<Group> group = groupRepository.findById(id);
        List<GroupUser> users = group.get().getUsers();

        for (GroupUser groupUser : users) {
            mongoTemplate.update(User.class)
                    .matching(Criteria.where("id").is(groupUser.getRoutingId()))
                    .apply(new Update().push("news").value(post))
                    .first();
        }

        mongoTemplate.update(Group.class)
                .matching(Criteria.where("id").is(id))
                .apply(new Update().push("groupPosts").value(groupPost))
                .first();

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(userId))
                .apply(new Update().push("news").value(post))
                .first();


        return post;
    }
}
