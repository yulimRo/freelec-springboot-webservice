package freelec.springboot2.webservice.web.dto;

import java.time.LocalDateTime;

import freelec.springboot2.webservice.domain.Posts;
import lombok.Getter;

@Getter
public class PostsListResponseDto {
	private Long id;
	private String title;
	private String author;
	private LocalDateTime modifiedDate;
	
	public PostsListResponseDto(Posts entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.author = entity.getAuthor();
		this.modifiedDate = entity.getModifiedDate();
	}
}
