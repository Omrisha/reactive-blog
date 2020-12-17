package il.ac.afeka.cloud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import il.ac.afeka.cloud.layout.PostBoundary;
import il.ac.afeka.cloud.layout.ProductBoundary;
import il.ac.afeka.cloud.layout.UserBoundary;

@SpringBootTest
public class TestPostBoundary {
	private String simpleJson = "{\r\n"
			+ "  \"user\":{\r\n"
			+ "    \"email\":\"customer11@shop.ping\"\r\n"
			+ "  }, \r\n"
			+ "  \"product\":{\r\n"
			+ "    \"id\":\"p12x\"\r\n"
			+ "  },  \r\n"
			+ "  \"postingTimestamp\":\"2020-12-10T04:12:39.053+0000\", \r\n"
			+ "  \"language\":\"en\", \r\n"
			+ "  \"postContent\":{\r\n"
			+ "    \"text\":\"I really like this product\"\r\n"
			+ "  }\r\n"
			+ "}";
	
	private String complexJson = "{\r\n"
			+ "  \"user\":{\r\n"
			+ "    \"email\":\"customerNumber1@shop.ping\"\r\n"
			+ "  }, \r\n"
			+ "  \"product\":{\r\n"
			+ "    \"id\":\"38996\"\r\n"
			+ "  },  \r\n"
			+ "  \"postingTimestamp\":\"2020-12-10T04:31:44.739+0000\", \r\n"
			+ "  \"language\":\"en\", \r\n"
			+ "  \"postContent\":{\r\n"
			+ "    \"image\":\"http://image.im/product38996.jpg\", \r\n"
			+ "    \"message\":\"This product changed my life!\", \r\n"
			+ "    \"details\":{\r\n"
			+ "      \"line1\":\"The fire consumed my apartment building\",\r\n"
			+ "      \"line2\":\"I had to move to a shelter\"\r\n"
			+ "    }, \r\n"
			+ "    \"references\":[\r\n"
			+ "      \"https://newscase.org/firebrokeduetomulfunctioninproduct\", \r\n"
			+ "      \"http://www.cnn.com\", \r\n"
			+ "      \"http://demoservice.de.mo/story?stodyId=985645211596037\"\r\n"
			+ "    ]\r\n"
			+ "  }, \r\n"
			+ "  \"extra\":\"hello\" \r\n"
			+ "}";
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testPostBoundaryDeserializing() throws JsonMappingException, JsonProcessingException {
		PostBoundary post = new ObjectMapper()
			      .readerFor(PostBoundary.class)
			      .readValue(this.simpleJson);
		
		assertEquals("customer11@shop.ping", post.getUser().getEmail());
		assertEquals("p12x", post.getProduct().getId());
		assertEquals("2020-12-10T04:12:39.053+0000", post.getPostingTimestamp());
		assertEquals("en", post.getLanguage());
		assertEquals("I really like this product", post.getPostContent().get("text"));
		
		post = new ObjectMapper()
			      .readerFor(PostBoundary.class)
			      .readValue(this.complexJson);
		
		assertEquals("hello", post.getMoreProperties().get("extra"));
	}
	
	@Test
	void testPostBoundarySerializing() throws JsonProcessingException {
		PostBoundary post = new PostBoundary();
		post.setUser(new UserBoundary("customerNumber1@shop.ping"));
		post.setProduct(new ProductBoundary("x23"));
		post.setLanguage("he");
		post.setPostingTimestamp("2020-12-10T04:12:39.053+0000");
		Map<String, Object> postContent = new HashMap<String, Object>();
		postContent.put("image", "http://image.im/product38996.jpg");
		Map<String, Object> moreProperties = new HashMap<String, Object>();
		moreProperties.put("extra", "hello");
		post.setPostContent(postContent);
		post.setMoreProperties(moreProperties);

	    String result = new ObjectMapper().writeValueAsString(post);
	    
	    // TODO add assertions
	}

}
