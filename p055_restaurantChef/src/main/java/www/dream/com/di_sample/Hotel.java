package www.dream.com.di_sample;

import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Repository // repository = 하나의정보 객체다
@RequiredArgsConstructor //의존성을 주입하는 생성자
public class Hotel {
	@NonNull @Getter
	private Chef chef;
}
