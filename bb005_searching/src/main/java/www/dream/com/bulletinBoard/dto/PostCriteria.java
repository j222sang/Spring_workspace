package www.dream.com.bulletinBoard.dto;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;
import www.dream.com.common.dto.Criteria;

@Data
public class PostCriteria extends Criteria{
	private String type;
	private String keyword;
	
	public String[] getTypeAsArr() {
		return type == null ? new String[] {} : type.split("");
	}
	
	public void getLink(UriComponentsBuilder builder) {
		builder.queryParam("type", getType())
				.queryParam("keyword", getKeyword());
		super.getLink(builder);
	}
}
