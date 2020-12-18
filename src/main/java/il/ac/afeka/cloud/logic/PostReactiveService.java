package il.ac.afeka.cloud.logic;

import il.ac.afeka.cloud.enums.FilterTypeEnum;
import il.ac.afeka.cloud.enums.SortByEnumaration;
import il.ac.afeka.cloud.layout.PostBoundary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostReactiveService {
    Mono<PostBoundary> create(PostBoundary value);

    Flux<PostBoundary> getPostsAllByUser(String email, FilterTypeEnum filterType, String filterValue, SortByEnumaration sortBy, String sortOrder);

    Flux<PostBoundary> getAllPostsByProduct(String productId, FilterTypeEnum filterType, String filterValue, SortByEnumaration sortBy, String sortOrder);

    Flux<PostBoundary> getAllPosts(FilterTypeEnum filterType, String filterValue, SortByEnumaration sortBy, String sortOrder);

    void delete();

}
