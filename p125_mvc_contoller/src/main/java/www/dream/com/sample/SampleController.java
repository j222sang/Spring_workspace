package www.dream.com.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import www.dream.com.sample.model.SampleVO;

@Controller // Servlet Component로 Container에 담기기 위해서는 우리가 어디를 더 보고 확인해야 하는가? ->
//Servlet.xml을 확인하자
@RequestMapping("/sample/*") // 현재클래스의 모든 메소드들의 기본적인 URL 경로가 된다
public class SampleController {
	/**
	 * sample/을 부르면 작동
	 */
	@RequestMapping("")
	public void basic() {
		System.out.println("basic()을 실행 하는군요");
	}

	/**
	 * sample/gp를 get또는 post방식으로 부르면 작동
	 */
	@RequestMapping(value = "gp", method = { RequestMethod.GET, RequestMethod.POST })
	public void basic4getAndPost() {
		System.out.println("basic4getAndPost()을 실행 하는군요");
	}

	/**
	 * sample/gp를 get방식으로 부르면 작동
	 */
	@GetMapping(value = "get")
	public void basic4getOnly() {
		System.out.println("basic4getOnly()을 실행 하는군요");
	}

	/**
	 * sample/get를 post방식으로 부르면 작동
	 */
	@PostMapping(value = "post")
	public void basic4postOnly() {
		System.out.println("basic4postOnly()을 실행 하는군요");
	}

	/**
	 * sample/param를 get방식으로 부를때 작동하면서 인자 까지 변수로
	 * 자동 형변환해 준다
	 */
	@GetMapping(value = "param")
	public void basic4ReqParam(@RequestParam("name") String name, @RequestParam("ag") int age) {
		System.out.println("name = " + name);
		System.out.println("age = " + age);
	}

	/**
	 * sample/vo를 get방식으로 부를때 작동하면서 인자와 객체 속성이름을 짝지어서 객체까지 만들어 준다
	 */
	@GetMapping(value = "vo")
	public void basic4SampleVO(SampleVO obj) {
		System.out.println(obj);
	}
}
