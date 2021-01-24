package il.ac.afeka.cloud.data;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostDao extends PagingAndSortingRepository<PostEntity, String> {
	List<PostEntity> findAllByUser(@Param("user") UserEntity user, Pageable request);
	List<PostEntity> findAllByProduct(@Param("product") ProductEntity product, Pageable request);
}
