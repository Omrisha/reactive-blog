package il.ac.afeka.cloud;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import il.ac.afeka.cloud.data.PostDao;
import il.ac.afeka.cloud.data.PostEntity;
import il.ac.afeka.cloud.layout.PostBoundary;

@SpringBootTest
public class TestPostDao {
	private PostDao postDao;
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
			+ "    \"extra\":{\r\n"
			+ "      \"extra1\":\"The fire\",\r\n"
			+ "      \"extra2\":\"I had\"\r\n"
			+ "    } \r\n"
			+ "}";
	
	@Autowired
	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}
	
	@BeforeEach
	@AfterEach
	void tearDown() {
		this.postDao.deleteAll().block();
	}
	
	@Test
	void testCreatePost() throws JsonMappingException, JsonProcessingException {
		PostBoundary boundary = new ObjectMapper()
			      .readerFor(PostBoundary.class)
			      .readValue(this.simpleJson);
		PostEntity entity = boundary.toEntity();
		PostEntity saved = this.postDao.save(entity).block();
		
		boundary = new ObjectMapper()
			      .readerFor(PostBoundary.class)
			      .readValue(this.complexJson);
		entity = boundary.toEntity();
		saved = this.postDao.save(entity).block();
		
		// TODO Add assertions.
	}
	
}
