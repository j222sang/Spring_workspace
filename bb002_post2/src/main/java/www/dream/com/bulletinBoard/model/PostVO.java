package www.dream.com.bulletinBoard.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import www.dream.com.common.model.CommonMngVO;
import www.dream.com.party.model.Party;

/**
 * 게시글
 * 
 * @author j222_sang
 */
@Data
@NoArgsConstructor
public class PostVO extends CommonMngVO {
	private String id; // 아이디
	private String title; // 제목
	private String content; // 내용
	private int readCnt; // 조회수
	private int likeCnt; // 좋아요 수
	private int dislikeCnt; // 싫어요 수

	// navigability 항해 가능성 객체 접근성
	private Party writer;

 	public PostVO(String title, String content, Party writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "PostVO [id=" + id + ", title=" + title + ", content=" + content + ", readCnt=" + readCnt + ", likeCnt="
				+ likeCnt + ", dislikeCnt=" + dislikeCnt + ", writer=" + writer + ", toString()=" + super.toString()
				+ "]";
	}

}
