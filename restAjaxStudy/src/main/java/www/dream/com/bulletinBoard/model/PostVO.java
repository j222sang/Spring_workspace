package www.dream.com.bulletinBoard.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import www.dream.com.common.model.CommonMngVO;
import www.dream.com.framework.langPosAnalyzer.HashTarget;
import www.dream.com.framework.printer.AnchorTarget;
import www.dream.com.framework.printer.ClassPrintTarget;
import www.dream.com.framework.printer.PrintTarget;
import www.dream.com.party.model.Party;


/**
 * 
 * 게시글
 * @author "Wook"
 *
 */
@Data
@NoArgsConstructor
@ClassPrintTarget
public class PostVO extends CommonMngVO {
	/** DB 함수 get_id 참조 */
	public static final int ID_LENGTH = 5;
	@AnchorTarget
	private String id;	//아이디
	@HashTarget @PrintTarget(order=100, caption="제목", withAnchor = true)
	private String title;
	@HashTarget 
	private String content;	//내용
	@PrintTarget(order=300, caption="조회수")
	private int readCnt;
	private int likeCnt;	//좋아요
	private int dislikeCnt;	//싫어요
	//navigability 객체 접근성
	@HashTarget 
	private Party writer; //작성자
		
	public PostVO(String title, String content, Party writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
	@Override
	public String toString() {
		return "PostVO [id=" + id + ", title=" + title + ", content=" + content
				+ ", readCnt=" + readCnt + ", likeCnt="
				+ likeCnt + ", dislikeCnt=" + dislikeCnt + ", writer="
				+ writer +  super.toString() + "]";
	}


	
}
