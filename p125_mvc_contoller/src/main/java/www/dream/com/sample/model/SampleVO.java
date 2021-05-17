package www.dream.com.sample.model;

import org.springframework.stereotype.Repository;

import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * VO : Value Object. 값을 담고 있는 객체. 화면에서부터 table까지 활용되는 것.
 * DTO : Data Trasfer Object = VO
 * @author User
 *
 */
@NoArgsConstructor
public class SampleVO {
	@Setter
	private String name;
	@Setter
	private int age;
	
	@Override
	public String toString() {
		return "SampleVO [name=" + name + ", age=" + age + "]";
	}
}
