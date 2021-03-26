package freelec.springboot2.webservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import freelec.springboot2.webservice.web.dto.PostsResponseDto;
import freelec.springboot2.webservice.web.dto.PostsSaveRequestDto;
import freelec.springboot2.webservice.web.dto.PostsUpdateRequestDto;
import freelec.springboot2.webservice.web.service.posts.PostsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
	
	@Autowired
	PostsService postsService;
	
	@PostMapping("/api/v1/posts")
	public Long save(@RequestBody PostsSaveRequestDto postsSaveRequestDto) {
		return postsService.save(postsSaveRequestDto); 
		
	}
	
	@GetMapping("/api/v1/posts/{id}")
	public PostsResponseDto findbyId(@PathVariable Long id) {
		return postsService.findById(id); 
		
	}
	
	@PutMapping("/api/v1/posts/{id}")
	public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
		return postsService.update(id,requestDto); 
		
	}
	
	@DeleteMapping("/api/v1/posts/{id}")
	public Long delete(@PathVariable Long id) {
		postsService.delete(id);
		return id;
	}
	
}
