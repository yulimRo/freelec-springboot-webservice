package freelec.springboot2.webservice.web.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class HelloResponseDtoTest {
	
	@Test
	public void lombokFnTest() {
		String name ="노유림";
		int amount = 10000;
		
		HelloResponseDto dto = new HelloResponseDto(name,amount);
		
		assertThat(dto.getName()).isEqualTo(name);
		assertThat(dto.getAmount()).isEqualTo(amount);
	}

}
