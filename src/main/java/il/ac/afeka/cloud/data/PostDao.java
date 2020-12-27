package il.ac.afeka.cloud.data;


import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;

public interface PostDao extends ReactiveMongoRepository<PostEntity, String> {
	
	public Flux<PostEntity> findAllByUser(UserEntity user, Sort sort);
	public Flux<PostEntity> findAllByProduct(ProductEntity product, Sort sort);
	
	public Flux<PostEntity> findAllByUserAndLanguage(UserEntity user, String language, Sort sort);
	public Flux<PostEntity> findAllByUserAndProduct(UserEntity user, ProductEntity product, Sort sort);
	
	public Flux<PostEntity> findAllByProductAndLanguage(ProductEntity product, String language, Sort sort);
}
