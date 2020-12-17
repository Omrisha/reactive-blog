package il.ac.afeka.cloud.layout;

public class ProductBoundary {
	private String id;

	public ProductBoundary() {
		super();
	}

	public ProductBoundary(String id) {
		super();
		this.id = id;
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
