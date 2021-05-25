package www.dream.com.bulletinBoard.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import www.dream.com.bulletinBoard.model.PostVO;
import www.dream.com.bulletinBoard.service.PostService;

@Controller // Servlet Component로 Container에 담기기 위해서는 우리가 어디를 더 보고 확인해야 하는가? ->
//Servlet.xml을 확인하자
@RequestMapping("/post/*") // 현재클래스의 모든 메소드들의 기본적인 URL 경로가 된다
public class PostController {
	@Autowired
	private PostService postService;
	
	/** 특정 게시판에 등록되어 있는 게시글을 목록으로 조회하기 */
	@GetMapping(value="list")
	public void listPost(@RequestParam("boardId") int boardId, Model model) {
		model.addAttribute("listPost", postService.getList(boardId));
		model.addAttribute("boardId", boardId);

	}

	@GetMapping(value="readPost")
	public void findPostById(@RequestParam("boardId") int boardId, 
			@RequestParam("postId") String id, Model model) {
		model.addAttribute("post", postService.findPostById(id));
		model.addAttribute("boardId", boardId);
	}
	
	@PostMapping(value="removePost")
	public String removePost(@RequestParam("boardId") int boardId, 
			@RequestParam("postId")String id, RedirectAttributes rttr) {
		if (postService.deletePostById(id)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/post/list?boardId=" + boardId;
	}
}
