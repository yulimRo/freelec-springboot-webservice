package freelec.springboot2.webservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import freelec.springboot2.webservice.domain.posts.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
public class Posts extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 500, nullable = false)
	private String title;
	
	@Column(columnDefinition="TEXT", nullable = false)
	private String content;
	
	private String author;
	
	@Builder
	public Posts(String title, String content, String author) {
		this.author = author;
		this.content = content;
		this.title = title;	
	}
	
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
