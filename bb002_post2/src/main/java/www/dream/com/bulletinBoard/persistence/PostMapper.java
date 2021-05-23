package www.dream.com.bulletinBoard.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import www.dream.com.bulletinBoard.model.BoardVO;
import www.dream.com.bulletinBoard.model.PostVO;

public interface PostMapper {
	// LRCUD
	/* mapper 함수의 인자 개수가 여러개 일 때는 필수적으로 @param을 넣어야함
	 * 이를 실수 하지 않기 위하여 한개여도 명시적으로 넣어 준다 */
	public List<PostVO> getList(@Param("boardId") int boardId);
	
	public int insert(@Param("board")BoardVO board, @Param("post") PostVO post);
}
