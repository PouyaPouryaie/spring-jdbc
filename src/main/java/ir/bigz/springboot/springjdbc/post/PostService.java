package ir.bigz.springboot.springjdbc.post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> findAll();
//    Iterable<Post> findAll();

    Optional<Post> findById(String id);

    void create(Post post);

    void update(Post post, String id);

    void delete(String id);
}
