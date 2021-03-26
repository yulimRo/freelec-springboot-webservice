package freelec.springboot2.webservice.domin.posts;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import freelec.springboot2.webservice.domain.Posts;
import freelec.springboot2.webservice.domain.PostsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void postGetSave() {
		String title = "테스트 제목";
		String content = "테스트 내용";
		postsRepository.save(Posts.builder().title(title).author("테스트 작성자").content(content).build());
		
		List<Posts> posts = postsRepository.findAll();
		Posts post = posts.get(0);
		
		assertThat(post.getTitle()).isEqualTo(title);
		assertThat(post.getContent()).isEqualTo(content);
		
	}
	
	@Test
	public void BaseTimeEntity_enroll() {
		LocalDateTime now = LocalDateTime.of(2021, 02,27,0,0,0);
		
		postsRepository.save(Posts.builder().title("title").content("content").author("author").build());
		List<Posts> all = postsRepository.findAll();
		Posts posts = all.get(0);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>> create = "+ posts.getCreatedDate() +", modifiedDate = "+ posts.getModifiedDate());
		
		assertThat(posts.getCreatedDate()).isAfter(now);
		assertThat(posts.getModifiedDate()).isAfter(now);
	}
}
