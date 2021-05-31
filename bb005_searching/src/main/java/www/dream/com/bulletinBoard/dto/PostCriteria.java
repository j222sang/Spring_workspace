package www.dream.com.bulletinBoard.dto;

import lombok.Data;
import www.dream.com.common.dto.Criteria;

@Data
public class PostCriteria extends Criteria{
	private String type;
	private String keyword;
	
	public String[] getTypeAsArr() {
		return type == null ? new String[] {} : type.split("");
	}
}
