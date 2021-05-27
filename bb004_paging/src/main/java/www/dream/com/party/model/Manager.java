package www.dream.com.party.model;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 시스템 운영자
 * @author j222_sang
 */
@Data
@NoArgsConstructor
public class Manager extends Party {
	
	public Manager(String userId) {
		super(userId);
		// TODO Auto-generated constructor stub
	}

	
}
