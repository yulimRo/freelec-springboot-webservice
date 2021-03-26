package freelec.springboot2.webservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import freelec.springboot2.webservice.web.dto.PostsResponseDto;
import freelec.springboot2.webservice.web.service.posts.PostsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
	
	@Autowired
	PostsService postsService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("posts",postsService.findAllDesc());
		return "index";
	}
	
	@GetMapping("/posts/save")
	public String postsSave() {
		return "posts-save";
	}
	
	@GetMapping("/posts/update/{id}")
	public String postsUpdate(@PathVariable Long id,Model model) {
		PostsResponseDto dto = postsService.findById(id);
		model.addAttribute("post",dto);
		return "posts-update";				
	}
}
