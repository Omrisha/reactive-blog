package il.ac.afeka.cloud.data;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class ProductEntity {
	@NotNull
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
