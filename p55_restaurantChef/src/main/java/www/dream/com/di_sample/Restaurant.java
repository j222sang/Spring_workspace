package www.dream.com.di_sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Repository
//기본생성자
@NoArgsConstructor

public class Restaurant {
	// 의존성 주입
	@Autowired
	@Getter
	private Chef chef;

}
