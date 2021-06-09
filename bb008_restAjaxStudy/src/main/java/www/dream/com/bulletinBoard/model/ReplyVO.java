package www.dream.com.bulletinBoard.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import www.dream.com.common.model.CommonMngVO;
import www.dream.com.framework.langPosAnalyzer.HashTarget;
import www.dream.com.framework.printer.AnchorTarget;
import www.dream.com.framework.printer.ClassPrintTarget;
import www.dream.com.framework.printer.PrintTarget;
import www.dream.com.framework.util.ToStringSuperHelp;
import www.dream.com.party.model.Party;

/**
 * 
 * 게시글
 * @author "j222_sang"
 *
 */
@Data
@NoArgsConstructor
@ClassPrintTarget
public class ReplyVO extends CommonMngVO {
	/** DB 함수 get_id 참조 */
	public static final int ID_LENGTH = 5;
	@AnchorTarget
	private String id;	//아이디
	@HashTarget 
	private String content;	//내용
	@HashTarget 
	private Party writer; //작성자
	
	
	public ReplyVO(String parentId, String content, Party writer) {
		this.content = content;
		this.writer = writer;
	}

	public ReplyVO(String content, Party writer) {
		this.content = content;
		this.writer = writer;
	}

	
	@Override
	public String toString() {
		return "ReplyVO [id=" + id + ", content=" + content+ ", writer=" + writer 
				+  ToStringSuperHelp.trimSuperString(super.toString()) + "]";
	}
	
}
