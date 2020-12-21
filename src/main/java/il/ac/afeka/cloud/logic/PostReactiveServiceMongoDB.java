package il.ac.afeka.cloud.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import il.ac.afeka.cloud.data.PostDao;
import il.ac.afeka.cloud.enums.FilterTypeEnum;
import il.ac.afeka.cloud.enums.SortByEnumaration;
import il.ac.afeka.cloud.layout.PostBoundary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostReactiveServiceMongoDB implements PostReactiveService {
	private PostDao postDao;
	
	@Autowired
	public PostReactiveServiceMongoDB(PostDao postDao) {
		this.postDao = postDao;
	}

	@Override
	public Mono<PostBoundary> create(PostBoundary value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<PostBoundary> getPostsAllByUser(String email, FilterTypeEnum filterType, String filterValue,
			SortByEnumaration sortBy, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<PostBoundary> getAllPostsByProduct(String productId, FilterTypeEnum filterType, String filterValue,
			SortByEnumaration sortBy, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<PostBoundary> getAllPosts(FilterTypeEnum filterType, String filterValue, SortByEnumaration sortBy,
			String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

}
