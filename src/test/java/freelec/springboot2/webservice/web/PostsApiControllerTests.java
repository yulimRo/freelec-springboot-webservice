package freelec.springboot2.webservice.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import freelec.springboot2.webservice.domain.Posts;
import freelec.springboot2.webservice.domain.PostsRepository;
import freelec.springboot2.webservice.web.dto.PostsSaveRequestDto;
import freelec.springboot2.webservice.web.dto.PostsUpdateRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTests {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void PostsSaveTest() {
		String title = "title";
		String content = "content";
		
		String url = "http://localhost:"+port+"/api/v1/posts";
		
		PostsSaveRequestDto dto = PostsSaveRequestDto.builder().title(title).content(content).author("author").build();
		
		ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, dto, Long.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isGreaterThan(0L);
		List<Posts> all = postsRepository.findAll();
		assertThat(all.get(0).getTitle()).isEqualTo(title);
		assertThat(all.get(0).getContent()).isEqualTo(content);
	}
	
	@Test
	public void update_post() throws Exception {
		Posts savePosts = postsRepository.save(Posts.builder().title("테스트 제목").content("테스트 내용").author("테스트 작성자").build());
		
		Long updateId = savePosts.getId();
		String updateTitle = "변경 제목";
		String updateContent = "변경 내용";
		
		PostsUpdateRequestDto requestdto = PostsUpdateRequestDto.builder().title(updateTitle).content(updateContent).build();
		
		String url = "http://localhost:"+port+"/api/v1/posts/"+ updateId;
		
		HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestdto);
		ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
		
		
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isGreaterThan(0L);
		List<Posts> all = postsRepository.findAll();
		assertThat(all.get(0).getTitle()).isEqualTo(updateTitle);
		assertThat(all.get(0).getContent()).isEqualTo(updateContent);
	}

}
