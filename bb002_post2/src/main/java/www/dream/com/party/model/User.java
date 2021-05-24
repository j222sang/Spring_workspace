package www.dream.com.party.model;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원
 * @author j222_sang
 *
 */
@Data
@NoArgsConstructor
public class User extends Party {
	public User(String userId) {
		super(userId);
	}
}
