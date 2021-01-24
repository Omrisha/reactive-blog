package il.ac.afeka.cloud.layout;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import il.ac.afeka.cloud.data.PostEntity;
import il.ac.afeka.cloud.data.ProductEntity;
import il.ac.afeka.cloud.data.UserEntity;

public class PostBoundary {
	private UserBoundary user;
	private ProductBoundary product;
	private Date postingTimestamp;
	private String language;
	private Map<String, Object> postContent;
	
	public PostBoundary() {
	}

	public PostBoundary(PostEntity entity) {
		super();

		if (entity.getUser() != null)
			this.user = new UserBoundary(entity.getUser());
		if (entity.getProduct() != null)
			this.product = new ProductBoundary(entity.getProduct());
		if (entity.getPostingTimestamp() != null)
			this.postingTimestamp = entity.getPostingTimestamp();
		if (entity.getLanguage() != null)
			this.language = entity.getLanguage();
		this.postContent = entity.getPostContent();
	}

	public PostBoundary(UserBoundary user, ProductBoundary product, Date postingTimestamp, String language,
			Map<String, Object> postContent) {
		super();
		this.user = user;
		this.product = product;
		this.postingTimestamp = postingTimestamp;
		this.language = language;
		this.postContent = postContent;
	}

	public UserBoundary getUser() {
		return user;
	}

	public void setUser(UserBoundary user) {
		this.user = user;
	}

	public ProductBoundary getProduct() {
		return product;
	}

	public void setProduct(ProductBoundary product) {
		this.product = product;
	}

	public Date getPostingTimestamp() {
		return postingTimestamp;
	}

	public void setPostingTimestamp(Date postingTimestamp) {
		this.postingTimestamp = postingTimestamp;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Map<String, Object> getPostContent() {
		return postContent;
	}

	public void setPostContent(Map<String, Object> postContent) {
		this.postContent = postContent;
	}

	@Override
	public String toString() {
		return "PostBoundary [user=" + user + ", product=" + product + ", postingTimestamp=" + postingTimestamp
				+ ", language=" + language + ", postContent=" + postContent + "]";
	}
	
	public PostEntity toEntity() {
		PostEntity entity = new PostEntity();
		entity.setUser(new UserEntity(this.getUser().getEmail()));
		entity.setProduct(new ProductEntity(this.getProduct().getId()));
		entity.setPostingTimestamp(this.getPostingTimestamp());
		entity.setLanguage(this.getLanguage());

		if (this.postContent != null)
			entity.setPostContent(new HashMap<String, Object>(this.postContent));

		return entity;		
	}

}
