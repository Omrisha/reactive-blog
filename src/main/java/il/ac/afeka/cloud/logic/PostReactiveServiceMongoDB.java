package il.ac.afeka.cloud.logic;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import il.ac.afeka.cloud.data.PostDao;
import il.ac.afeka.cloud.data.ProductEntity;
import il.ac.afeka.cloud.data.UserEntity;
import il.ac.afeka.cloud.enums.FilterTypeEnum;
import il.ac.afeka.cloud.enums.SortByEnumaration;
import il.ac.afeka.cloud.enums.TimeEnum;
import il.ac.afeka.cloud.layout.PostBoundary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostReactiveServiceMongoDB implements PostReactiveService {
	private final String DEFAULT_SECONDARY_SORT = "_id";
	private PostDao postDao;
	
	@Autowired
	public PostReactiveServiceMongoDB(PostDao postDao) {
		this.postDao = postDao;
	}

	@Override
	public Mono<PostBoundary> create(PostBoundary boundary) {
		return Mono.just(boundary).map(p -> {
				p.setPostingTimestamp(new Date());
				return p;
			})
			.map(PostBoundary::toEntity)
			.flatMap(entity -> this.postDao.save(entity))
			.map(PostBoundary::new);
	}

	@Override
	public Flux<PostBoundary> getPostsAllByUser(String email, FilterTypeEnum filterType, String filterValue,
			SortByEnumaration sortBy, String sortOrder) {
		
		Sort.Direction dir = sortOrder.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
		
		if (filterType != null)		
			switch (filterType) {
			case byProduct:
				return this.getAllPostByUserAndProduct(
						new UserEntity(email),
						new ProductEntity(filterValue),
						dir,
						sortBy.name());
			case byLanguage:
				return this.getAllPostsByUserAndLanguage(
						new UserEntity(email),
						filterValue,
						dir,
						sortBy.name());
			case byCreation:
				TimeEnum time = TimeEnum.valueOf(filterValue);
				return this.getAllPostsByUserAndCreation(
						new UserEntity(email),
						time,
						dir,
						sortBy.name());
			default:
				break;
			}

		
		return this.postDao.findAllByUser(
				new UserEntity(email),
				Sort.by(
						dir,
						sortBy.name(),
						DEFAULT_SECONDARY_SORT))
				.map(PostBoundary::new);
	}

	@Override
	public Flux<PostBoundary> getAllPostsByProduct(String productId, FilterTypeEnum filterType, String filterValue,
			SortByEnumaration sortBy, String sortOrder) {
		
		Sort.Direction dir = sortOrder.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
		
		if (filterType != null)
			switch (filterType) {
			case byLanguage:
				return this.getAllPostsByProductAndLanguage(
						new ProductEntity(productId),
						filterValue,
						dir,
						sortBy.name());
			case byCreation:
				TimeEnum time = TimeEnum.valueOf(filterValue);
				return this.getAllPostsByProductAndCreation(
						new ProductEntity(productId),
						time,
						dir,
						sortBy.name());

			default:
				break;
			}
		
		Flux<PostBoundary> flux = this.postDao.findAllByProduct(
				new ProductEntity(productId),
				Sort.by(
						dir,
						sortBy.name(),
						DEFAULT_SECONDARY_SORT))
				.map(PostBoundary::new);
		
		return flux;
	}

	@Override
	public Flux<PostBoundary> getAllPosts(FilterTypeEnum filterType, String filterValue, SortByEnumaration sortBy,
			String sortOrder) {		
		Sort.Direction dir = sortOrder.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
		
		if (filterType != null)
			switch (filterType) {
			case byCreation:
				TimeEnum time = TimeEnum.valueOf(filterValue);
				return this.getAllPostsByCreation(
						dir,
						sortBy.name(),
						time);
			case byCount:
				return this.getAllPostsByCount(Long.parseLong(filterValue));
	
			default:
				break;
			}
		
		Flux<PostBoundary> flux = this.postDao
				.findAll(Sort.by(
						sortOrder.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC,
						sortBy.name(),
						DEFAULT_SECONDARY_SORT))
				.map(PostBoundary::new);
		
		return flux;
	}

	private Flux<PostBoundary> getAllPostsByCount(long count) {
		return this.postDao
				.findAll(Sort.by(
						Sort.Direction.DESC,
						SortByEnumaration.postingTimestamp.name(),
						DEFAULT_SECONDARY_SORT))
				.limitRequest(count)
				.map(PostBoundary::new);
	}

	private Flux<PostBoundary> getAllPostsByCreation(Direction dir, String sortBy, TimeEnum timeFilter) {
		Flux<PostBoundary> flux = this.postDao
				.findAll(Sort.by(
						dir,
						sortBy,
						DEFAULT_SECONDARY_SORT))
				.map(PostBoundary::new);
		
		return this.filterByCreation(flux, timeFilter);
	}

	@Override
	public void delete() {
		this.postDao.deleteAll();

	}

	private Flux<PostBoundary> getAllPostsByUserAndCreation(UserEntity userEntity, TimeEnum timeFilter, Direction dir,
			String sortBy) {	
		Flux<PostBoundary> flux = this.postDao.findAllByUser(
				userEntity,
				Sort.by(
						dir,
						sortBy,
						DEFAULT_SECONDARY_SORT))
				.map(PostBoundary::new);
		
		return this.filterByCreation(flux, timeFilter);
	}

	private Flux<PostBoundary> getAllPostsByUserAndLanguage(UserEntity userEntity, String lang, Direction dir,
			String sortBy) {
		return this.postDao.findAllByUserAndLanguage(userEntity, lang, Sort.by(
				dir,
				sortBy,
				DEFAULT_SECONDARY_SORT))
		.map(PostBoundary::new);
	}

	private Flux<PostBoundary> getAllPostByUserAndProduct(UserEntity userEntity, ProductEntity productEntity, Sort.Direction dir, String sortBy) {
		return this.postDao.findAllByUserAndProduct(userEntity, productEntity, Sort.by(
				dir,
				sortBy,
				DEFAULT_SECONDARY_SORT))
		.map(PostBoundary::new);
	}

	private Flux<PostBoundary> getAllPostsByProductAndLanguage(ProductEntity productEntity, String lang,
			Direction dir, String sortBy) {
		return this.postDao.findAllByProductAndLanguage(
				productEntity,
				lang,
				Sort.by(
						dir,
						sortBy,
						DEFAULT_SECONDARY_SORT))
				.map(PostBoundary::new);
	}

	private Flux<PostBoundary> getAllPostsByProductAndCreation(ProductEntity productEntity, TimeEnum timeFilter,
			Direction dir, String sortBy) {
		Flux<PostBoundary> flux = this.postDao.findAllByProduct(
				productEntity,
				Sort.by(
						dir,
						sortBy,
						DEFAULT_SECONDARY_SORT))
				.map(PostBoundary::new);
		
		return this.filterByCreation(flux, timeFilter);
	}

	private Flux<PostBoundary> filterByCreation(Flux<PostBoundary> flux, TimeEnum timeFilter) {
		long timeToSubstract = timeFilter.millisecs();
		return flux.filter(p -> p.getPostingTimestamp()
				.compareTo(new Date(System.currentTimeMillis() - timeToSubstract)) > 0);
	}

}
