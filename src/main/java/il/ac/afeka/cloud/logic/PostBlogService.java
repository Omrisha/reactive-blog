package il.ac.afeka.cloud.logic;

import il.ac.afeka.cloud.enums.FilterTypeEnum;
import il.ac.afeka.cloud.enums.SortByEnumaration;
import il.ac.afeka.cloud.layout.PostBoundary;

import java.util.List;

public interface PostBlogService {
    PostBoundary create(PostBoundary value);

    List<PostBoundary> getPostsAllByUser(String email, FilterTypeEnum filterType, String filterValue, SortByEnumaration sortBy, String sortOrder, int page, int size);

    List<PostBoundary> getAllPostsByProduct(String productId, FilterTypeEnum filterType, String filterValue, SortByEnumaration sortBy, String sortOrder, int page, int size);

    List<PostBoundary> getAllPosts(FilterTypeEnum filterType, String filterValue, SortByEnumaration sortBy, String sortOrder, int page, int size);

    void delete();
}
