package il.ac.afeka.cloud.data;

import org.springframework.data.annotation.Id;

public class ProductEntity {
	
	private String id;

	public ProductEntity() {
		super();
	}

	public ProductEntity(String id) {
		super();
		this.id = id;
	}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ProductEntity [id=" + id + "]";
	}

}
