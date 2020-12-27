package il.ac.afeka.cloud.layout;

import il.ac.afeka.cloud.enums.FilterTypeEnum;
import il.ac.afeka.cloud.enums.SortByEnumaration;
import il.ac.afeka.cloud.logic.PostReactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BlogController {
    PostReactiveService service;

    @Autowired
    public BlogController(PostReactiveService service) {
        this.service = service;
    }

    @RequestMapping(path = "/blog",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PostBoundary> create(@RequestBody PostBoundary value) {
        return this.service.create(value);
    }

    @RequestMapping(path = "/blog/byUser/{email}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PostBoundary> getAllPostsByUser(
            @PathVariable("email") String email,
            @RequestParam(name = "filterType", required = false, defaultValue = "") FilterTypeEnum filterType,
            @RequestParam(name = "filterValue", required = false, defaultValue = "") String filterValue,
            @RequestParam(name = "sortBy", required = false, defaultValue = "postingTimestamp") SortByEnumaration sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder) {
        return this.service.getPostsAllByUser(email, filterType, filterValue, sortBy, sortOrder);
    }

    @RequestMapping(path = "/blog/byProduct/{productId}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PostBoundary> getAllPostByProduct(
            @PathVariable("productId") String productId,
            @RequestParam(name = "filterType", required = false, defaultValue = "") FilterTypeEnum filterType,
            @RequestParam(name = "filterValue", required = false, defaultValue = "") String filterValue,
            @RequestParam(name = "sortBy", required = false, defaultValue = "postingTimestamp") SortByEnumaration sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder
    ) {
        return this.service.getAllPostsByProduct(
                productId,
                filterType,
                filterValue,
                sortBy,
                sortOrder
        );
    }

    @RequestMapping(path = "/blog",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PostBoundary> getAllPosts(
            @RequestParam(name = "filterType", required = false, defaultValue = "") FilterTypeEnum filterType,
            @RequestParam(name = "filterValue", required = false, defaultValue = "") String filterValue,
            @RequestParam(name = "sortBy", required = false, defaultValue = "postingTimestamp") SortByEnumaration sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder
    ) {
        return this.service.getAllPosts(filterType, filterValue, sortBy, sortOrder);
    }

    @RequestMapping(path = "/blog",
            method = RequestMethod.DELETE)
    public Mono<Void> delete() {
        this.service.delete();
        return Mono.empty();
    }
}
