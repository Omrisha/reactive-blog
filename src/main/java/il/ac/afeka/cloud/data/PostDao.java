package il.ac.afeka.cloud.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PostDao extends ReactiveCrudRepository<PostEntity, String> {

}
