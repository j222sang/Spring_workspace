package www.dream.com.bulletinBoard.persistence;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import www.dream.com.bulletinBoard.model.BoardVO;
import www.dream.com.bulletinBoard.model.PostVO;
import www.dream.com.common.dto.Criteria;
import www.dream.com.party.model.Admin;
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
    	  PostVO post = new PostVO("아름다운 강산2", "제제젲제제333333시절시절", new Admin("admin"));
    	  System.out.println(post.toString());
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
 
}