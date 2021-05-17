package www.dream.com.di_sample;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Repository
@NoArgsConstructor
// 객체가 활동하는 범위를 정해줌
@Scope("prototype")
public class Chef {
	@Setter @Getter
	private String name = " ";
	@Override 
	public String toString() {
		return "멋진" + name + "주방장님" ;
	}

}