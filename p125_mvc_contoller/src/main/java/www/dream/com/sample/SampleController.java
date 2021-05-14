package www.dream.com.sample;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import www.dream.com.sample.model.SVO4Debugging;
import www.dream.com.sample.model.SampleMasterVo;
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
	
	@GetMapping(value = "vo4d")
	public void basic4SVO4Debugging(SVO4Debugging obj) {
		System.out.println(obj);
	}
	/**
	 *  List<String> interface로는 객체 생성 못해서 오류남
	 *  @RequestParam("ids") 설정안해주면 list객체로 매핑 안됨 
	 * @param ids
	 */
	@GetMapping(value = "list")
	public void basic4list(@RequestParam("ids") ArrayList<String> ids) {
		for (String str : ids) {
			System.out.println(str);
		}
	}
	/**
	 * 배열의 경우 동일 이름이 @RequestParam이 없어도 구동된다.. ArrayList 더 편리
	 * @param ids
	 */
	@GetMapping(value = "arr")
	public void basic4Array(@RequestParam("ids") String[] ids) {
		for (String str : ids) {
			System.out.println(str);
		}
	}

	/**
	 * sample/masterDetail?id=0001&listSampleVO%5B0%5D.name=bbb&listSampleVO%5B0%5D.age=555
	  &id=0002&listSampleVO%5B1%5D.name=홍길동&listSampleVO%5B1%5D.age=123456
	 * @param obj
	 */
	@GetMapping(value = "masterDetail")
	public void basic4masterDetail(SampleMasterVo obj) {
		System.out.println(obj);
	}
}
