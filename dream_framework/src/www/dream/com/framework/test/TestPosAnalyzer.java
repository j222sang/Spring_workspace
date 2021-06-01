package www.dream.com.framework.test;

import java.util.Map;

import org.junit.Test;

import www.dream.com.framework.langPosAnalyzer.PosAnalyzer;

public class TestPosAnalyzer {

	@Test
	public void test() {
		Post post = new Post();
		post.setTitle("질문");
		post.setContent("안녕하세요 컴퓨터 지갑 컴퓨터 마우스 키보드 안녕");
		Party writer = new Party();
		writer.setName("홍길동");
		post.setWriter(writer);
		
		ContactPoint cp = new ContactPoint();
		cp.setInfo("사자 고양이 코끼리");
		writer.addCP(cp);
		cp = new ContactPoint();
		cp.setInfo("wltkd96@naver.com");
		writer.addCP(cp);
		
		Map<String, Integer> map = PosAnalyzer.getHashTags(post);
		for (String k : map.keySet()) {
			System.out.println(k + " : " + map.get(k));
		}
	}
}
