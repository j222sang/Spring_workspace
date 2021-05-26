package www.dream.com.bulletinBoard.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import www.dream.com.bulletinBoard.model.BoardVO;
import www.dream.com.bulletinBoard.model.PostVO;
import www.dream.com.bulletinBoard.service.BoardService;
import www.dream.com.bulletinBoard.service.PostService;
import www.dream.com.party.model.Party;
import www.dream.com.party.model.User;

@Controller // Servlet Component로 Container에 담기기 위해서는 우리가 어디를 더 보고 확인해야 하는가? ->
//Servlet.xml을 확인하자
@RequestMapping("/post/*") // 현재클래스의 모든 메소드들의 기본적인 URL 경로가 된다
public class PostController {
	@Autowired
	private BoardService boardService;
	
	//LRCUD 순서 유지
	@Autowired
	private PostService postService;
	
	/** 특정 게시판에 등록되어 있는 게시글을 목록으로 조회하기 */
	@GetMapping(value="list")
	public void listPost(@RequestParam("boardId") int boardId, Model model) {
		model.addAttribute("listPost", postService.getList(boardId));
		model.addAttribute("boardId", boardId);
		model.addAttribute("boardName", boardService.getBoard(boardId).getName());
	}

	@GetMapping(value={"readPost", "modifyPost"})
	public void findPostById(@RequestParam("boardId") int boardId, 
			@RequestParam("postId") String id, Model model) {
		model.addAttribute("post", postService.findPostById(id));
		model.addAttribute("boardId", boardId);
	}
	
	/** 게시글 정보 등록 */
	@GetMapping(value="registerPost")
	public void registerPost(@RequestParam("boardId") int boardId, Model model) {
		model.addAttribute("boardId", boardId);
	}

	@PostMapping(value="registerPost")
	public String registerPost(@RequestParam("boardId") int boardId,
			PostVO newPost, RedirectAttributes rttr) {
		BoardVO board = new BoardVO(boardId);
		Party write = new User("hong");
		newPost.setWriter(write);
		postService.insert(board, newPost);
		
		rttr.addFlashAttribute("result", newPost.getId());
		
		return "redirect:/post/list?boardId=" + boardId;
	}
	
	
	@PostMapping(value="modifyPost")
	public String modifyPost(@RequestParam("boardId") int boardId, PostVO modifiedPost
			, RedirectAttributes rttr) {
		
		if (postService.updatePost(modifiedPost)) {
			rttr.addFlashAttribute("result", "수정");
		}
		return "redirect:/post/list?boardId=" + boardId;
	}
	
	@PostMapping(value="removePost")
	public String removePost(@RequestParam("boardId") int boardId, 
			@RequestParam("postId")String id, RedirectAttributes rttr) {
		if (postService.deletePostById(id)) {
			rttr.addFlashAttribute("result", "삭제");
		}
		return "redirect:/post/list?boardId=" + boardId;
	}
}
