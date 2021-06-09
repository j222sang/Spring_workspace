package www.dream.com.restStudy.control;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import www.dream.com.bulletinBoard.model.BoardVO;
import www.dream.com.bulletinBoard.model.PostVO;
import www.dream.com.bulletinBoard.service.PostService;
import www.dream.com.common.dto.Criteria;
import www.dream.com.party.model.Party;
import www.dream.com.party.model.User;
import www.dream.com.restStudy.model.DetailVO;
import www.dream.com.restStudy.model.MyVO;

@RestController
@RequestMapping("/sample/*")
public class SampleController {
	
	@Autowired
	private PostService postService;
	
	/** 문자열 반환 연습 */
	@GetMapping(value = "getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		return "수고요";
	}


	/** 객체 반환 연습 */
	@GetMapping(value = "getObject", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public MyVO getObject() {
		MyVO ret = new MyVO();
		ret.setAa(2332);
		ret.setBb("hhihi");
		DetailVO d1 = new DetailVO();
		d1.setGgg(23424);
		d1.setUuuu("funny");
		ret.add(d1);
		d1 = new DetailVO();
		d1.setGgg(234444424);
		d1.setUuuu("fugggnny");
		ret.add(d1);
		d1 = new DetailVO();
		d1.setGgg(324);
		d1.setUuuu("fuqweqwenny");
		ret.add(d1);
		return ret;
	}

	/** 객체 목록 반환 연습 */
	@GetMapping(value = "getList")
	public List<MyVO> getList() {
		List<MyVO> ret = IntStream.range(0, 10).mapToObj(i->{
			MyVO newBie = new MyVO();
			newBie.setAa(2332);
			newBie.setBb("hhihi");
			DetailVO d1 = new DetailVO();
			d1.setGgg(23424);
			d1.setUuuu("funny");
			newBie.add(d1);
			d1 = new DetailVO();
			d1.setGgg(234444424);
			d1.setUuuu("fugggnny");
			newBie.add(d1);
			return newBie;
		}).collect(Collectors.toList());
		
		return ret;
	}
	
	/** ResponseEntity를 활용한 Map 반환 연습 */
	@GetMapping(value = "getREMap")
	public ResponseEntity<Map<String, MyVO>> getREMap() {
		Map<String, MyVO> map = IntStream.range(0, 10).mapToObj(i->{
			MyVO newBie = new MyVO();
			newBie.setAa(i);
			newBie.setBb("hhihi");
			DetailVO d1 = new DetailVO();
			d1.setGgg(23424);
			d1.setUuuu("funny");
			newBie.add(d1);
			d1 = new DetailVO();
			d1.setGgg(234444424);
			d1.setUuuu("fugggnny");
			newBie.add(d1);
			return newBie;
		}).collect(Collectors.toMap(MyVO::getKey, MyVO::getObj));
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}
	
	/**  MAV 반환 연습 */
	@GetMapping(value = "getJSP")
	public ModelAndView getJSP() {
		ModelAndView mav = new ModelAndView();
		MyVO newBie = new MyVO();
		newBie.setAa(11);
		newBie.setBb("hhihi");
		
		mav.getModel().put("data", newBie);
		mav.setViewName("sample/mav");
		return mav;
	}
	
	@GetMapping(value="JSPJSP")
	public ModelAndView JSPJSP() {
		ModelAndView mav = new ModelAndView();
		MyVO newBie = new MyVO();
		newBie.setAa(12);
		newBie.setBb("aa");
		
		mav.getModel().put("dt", newBie);
		mav.setViewName("sample/mav");
		
		return mav;
	}
	
	
	
	/**  MAV 반환 연습 */
	@GetMapping(value = "getReDirJSP")
	public ModelAndView getReDirJSP() {
		ModelAndView mav = new ModelAndView();
		MyVO newBie = new MyVO();
		newBie.setAa(11);
		newBie.setBb("hhihi");
		
		mav.getModel().put("data", newBie);
		mav.setViewName("redirect:/sample/redirectedPage");
		return mav;
	}
	
	/**  redirect되는 요청에 대하여 반응하는 handler  */
	@GetMapping(value = "redirectedPage")
	public ModelAndView getRedirectedPage() {
		ModelAndView mav = new ModelAndView();
		MyVO newBie = new MyVO();
		newBie.setAa(11);
		newBie.setBb("hhihi");
		
		mav.getModel().put("data", newBie);
		mav.setViewName("sample/redirectedPage");
		return mav;
	}
	
	/**  redirect되는 요청에 대하여 반응하는  */
	@GetMapping(value = "{boardId}/{id}")
	public ModelAndView findPostById(@PathVariable ("boardId") int boardId,
			@PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView();
		
		mav.getModel().put( "post", postService.findPostById(id));
		mav.getModel().put( "boardId", boardId);
		mav.getModel().put( "pagenation", new Criteria());
		
		mav.setViewName("post/readPost");
		return mav;
	}
	
	@PostMapping(value="registerPost/{boardId}")
	public PostVO registerPost(@PathVariable("boardId") int boardId,
			@RequestBody PostVO newPost) {
		BoardVO board = new BoardVO(boardId);
		Party writer = new User("hong");
		newPost.setWriter(writer);
		postService.insert(board, newPost);

		return postService.findPostById(newPost.getId());
		
	}
	
	/**  pathVariable과 함께 requestParameter로 요청 정보 처리 예*/
	@GetMapping(value = "{boardId}")
	public ModelAndView findPostByReqId(@PathVariable ("boardId") int boardId,
			@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView();
		
		mav.getModel().put( "post", postService.findPostById(id));
		mav.getModel().put( "boardId", boardId);
		mav.getModel().put( "pagenation", new Criteria());
		
		mav.setViewName("post/readPost");
		return mav;
	}
	
}
