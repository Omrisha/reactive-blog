package il.ac.afeka.cloud.logic;

import il.ac.afeka.cloud.data.PostDao;
import il.ac.afeka.cloud.data.PostEntity;
import il.ac.afeka.cloud.data.ProductEntity;
import il.ac.afeka.cloud.data.UserEntity;
import il.ac.afeka.cloud.enums.FilterTypeEnum;
import il.ac.afeka.cloud.enums.SortByEnumaration;
import il.ac.afeka.cloud.enums.TimeEnum;
import il.ac.afeka.cloud.layout.PostBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostBlogServiceImpl implements PostBlogService {
    private final String DEFAULT_SECONDARY_SORT = "_id";
    private PostDao postDao;

    @Autowired
    public PostBlogServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public PostBoundary create(PostBoundary value) {
        PostEntity entity = value.toEntity();
        entity.setPostingTimestamp(new Date());
        PostEntity rv = this.postDao.save(entity);
        return new PostBoundary(rv);
    }

    @Override
    public List<PostBoundary> getPostsAllByUser(String email, FilterTypeEnum filterType, String filterValue, SortByEnumaration sortBy, String sortOrder, int page, int size) {
        Sort.Direction dir = sortOrder.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest sortAttr = PageRequest.of(page, size, Sort.by(dir, sortBy.toString()));

        if (filterType != null)
            switch (filterType) {
                case byProduct:
                    return this.postDao.findAllByUser(new UserEntity(email), sortAttr)
                            .stream()
                            .filter(postEntity -> postEntity.getProduct().getId().equals(filterValue))
                            .map(PostBoundary::new)
                            .collect(Collectors.toList());
                case byLanguage:
                    return this.postDao.findAllByUser(new UserEntity(email), sortAttr)
                            .stream()
                            .filter(postEntity -> postEntity.getLanguage().equals(filterValue))
                            .map(PostBoundary::new)
                            .collect(Collectors.toList());
                case byCreation:
                    TimeEnum time = TimeEnum.valueOf(filterValue);
                    long timeToSubstract = time.millisecs();
                    return this.postDao.findAllByUser(new UserEntity(email), sortAttr)
                            .stream()
                            .filter(postEntity -> postEntity.getPostingTimestamp().compareTo(new Date(System.currentTimeMillis() - timeToSubstract)) > 0)
                            .map(PostBoundary::new)
                            .collect(Collectors.toList());
                default:
                    break;
            }

        return this.postDao.findAllByUser(new UserEntity(email), sortAttr)
                .stream()
                .map(PostBoundary::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostBoundary> getAllPostsByProduct(String productId, FilterTypeEnum filterType, String filterValue, SortByEnumaration sortBy, String sortOrder, int page, int size) {
        Sort.Direction dir = sortOrder.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest sortAttr = PageRequest.of(page, size, Sort.by(dir, sortBy.toString()));

        if (filterType != null)
            switch (filterType) {
                case byLanguage:
                    return this.postDao.findAllByProduct(new ProductEntity(productId), sortAttr)
                            .stream()
                            .filter(postEntity -> postEntity.getLanguage().equals(filterValue))
                            .map(PostBoundary::new)
                            .collect(Collectors.toList());
                case byCreation:
                    TimeEnum time = TimeEnum.valueOf(filterValue);
                    long timeToSubstract = time.millisecs();
                    return this.postDao.findAllByProduct(new ProductEntity(productId), sortAttr)
                            .stream()
                            .filter(postEntity -> postEntity.getPostingTimestamp().compareTo(new Date(System.currentTimeMillis() - timeToSubstract)) > 0)
                            .map(PostBoundary::new)
                            .collect(Collectors.toList());
                default:
                    break;
            }

        return this.postDao.findAllByProduct(new ProductEntity(productId), sortAttr)
                .stream()
                .map(PostBoundary::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostBoundary> getAllPosts(FilterTypeEnum filterType, String filterValue, SortByEnumaration sortBy, String sortOrder, int page, int size) {
        Sort.Direction dir = sortOrder.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest sortAttr = PageRequest.of(page, size, Sort.by(dir, sortBy.toString()));

        if (filterType != null)
            switch (filterType) {
                case byLanguage:
                    return this.postDao.findAll(sortAttr)
                            .stream()
                            .filter(postEntity -> postEntity.getLanguage().equals(filterValue))
                            .map(PostBoundary::new)
                            .collect(Collectors.toList());
                case byCreation:
                    TimeEnum time = TimeEnum.valueOf(filterValue);
                    long timeToSubstract = time.millisecs();
                    return this.postDao.findAll(sortAttr)
                            .stream()
                            .filter(postEntity -> postEntity.getPostingTimestamp().compareTo(new Date(System.currentTimeMillis() - timeToSubstract)) > 0)
                            .map(PostBoundary::new)
                            .collect(Collectors.toList());
                default:
                    break;
            }

        return this.postDao.findAll(sortAttr)
                .stream()
                .map(PostBoundary::new)
                .collect(Collectors.toList());
    }

    @Override
    public void delete() {
        this.postDao.deleteAll();
    }
}
