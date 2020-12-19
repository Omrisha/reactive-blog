package il.ac.afeka.cloud.layout;

import il.ac.afeka.cloud.data.ProductEntity;

public class ProductBoundary {
	private String id;

	public ProductBoundary() {
		super();
	}

	public ProductBoundary(String id) {
		super();
		this.id = id;
	}

	public ProductBoundary(ProductEntity entity) {
		super();
		if (entity.getId() != null)
			this.id = entity.getId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ProductBoundary [id=" + id + "]";
	}

}
