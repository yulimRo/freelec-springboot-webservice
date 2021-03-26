package freelec.springboot2.webservice.web.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import freelec.springboot2.webservice.domain.Posts;
import freelec.springboot2.webservice.domain.PostsRepository;
import freelec.springboot2.webservice.web.dto.PostsListResponseDto;
import freelec.springboot2.webservice.web.dto.PostsResponseDto;
import freelec.springboot2.webservice.web.dto.PostsSaveRequestDto;
import freelec.springboot2.webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {

	private final PostsRepository postsRepository;

	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당게시글이 없습니다. id = " + id));
		return new PostsResponseDto(entity);
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
		posts.update(requestDto.getTitle(), requestDto.getContent());
		return posts.getId();
	}

	@Transactional // (readOnly = true) 옵션있어야 함
	public List<PostsListResponseDto> findAllDesc() {
		return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
	}

	@Transactional
	public void delete(Long id) {
		Posts post = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
		postsRepository.delete(post);
	}

}
