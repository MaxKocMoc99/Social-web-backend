package KocMoc.social.web.users.groupRooms;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GroupRoomRepository extends MongoRepository<GroupRoom, ObjectId> {
    void deleteById(String id);

    Optional<GroupRoom> findById(String id);

    List<GroupRoom> findAllByRoutingId(String routingId);
}
