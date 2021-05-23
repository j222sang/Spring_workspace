package www.dream.com.party.bulletinBoard.persistence;

import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import www.dream.com.bulletinBoard.model.BoardVO;
import www.dream.com.bulletinBoard.model.PostVO;
import www.dream.com.bulletinBoard.persistence.PostMapper;
import www.dream.com.party.model.Admin;
import www.dream.com.party.model.Party;

@RunWith(SpringJUnit4ClassRunner.class)
//해당 위치에 있는 데이터를 바탕으로 데이터를 만들어라
@ContextConfiguration("file:src\\main\\webapp\\WEB-INF\\spring\\root-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostMapperTest {
	@Autowired
	private PostMapper postMapper;

	@Test
   public void test000InsertPost() {
      try {
         BoardVO commNotice = new BoardVO(1);
         PostVO post = new PostVO("아름다운 강산", "제3공화국 시절, 지금은 한국 록의 대부로 일컬어지는 신중현에게 당시 정권이었던 박정희를 찬양하는 노래를 만들라고 꾸준히 강요했다. 하나 신중현은 수차례의 협박에도 불구하고 이를 거절했고, 권력자를 찬양하는 노래는 만들 수 없지만 아름다운 우리 강산을 찬양하는 노래는 만들 수 있다는 의지의 표현으로 이 노래를 만들어 발표했다. 참고로 이후로 정권에 찍힌 신중현은 \"미인\" 같은 다수의 히트곡들이 줄줄이 금지곡이 되는 수모를 겪었으며, 이 곡도 금지곡이 되었다.", new Admin("admin"));
         postMapper.insert(commNotice, post);
         System.out.println("지금 만든 객체의 아이디는" + post.getId() + "책 191쪽 참고");
      }catch(Exception e) {
         e.printStackTrace();
      }
      
   }

	@Test
	public void test001GetBoard() {
		assertNotNull(postMapper);
		try {
			postMapper.getList(1).forEach(post -> {
				System.out.println(post);
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}