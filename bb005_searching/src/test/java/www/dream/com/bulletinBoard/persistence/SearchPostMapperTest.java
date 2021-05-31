package www.dream.com.bulletinBoard.persistence;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import www.dream.com.bulletinBoard.dto.PostCriteria;
@RunWith(SpringJUnit4ClassRunner.class)
//해당 위치에 있는 데이터를 바탕으로 데이터를 만들어라
@ContextConfiguration("file:src\\main\\webapp\\WEB-INF\\spring\\root-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchPostMapperTest {   
   @Autowired
   private PostMapper postMapper;
 
   //@Test
   public void test040GetList() {
      assertNotNull(postMapper);
      Map<String, String> map = new HashMap<>();
      map.put("T", "우리 회사의 2022년 목표 회원수는 20억명");
      try {
    	  postMapper.getListBySearch(1, map).forEach(post->{System.out.println(post);});
         
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
   
   @Test
   public void test042GetList() {
      assertNotNull(postMapper);
      PostCriteria cri = new PostCriteria();
      cri.setAmount(10);
      cri.setPageNumber(2);
      cri.setType("TC");
      cri.setKeyword("우리 회사의 2022년 목표 회원수는 20억명");
      try {
    	  postMapper.getListBySearchWithPaging(1, cri).forEach(post->{System.out.println(post);});
         
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
   
   @Test
   public void test044GetList() {
      assertNotNull(postMapper);
      PostCriteria cri = new PostCriteria();
      cri.setAmount(10);
      cri.setPageNumber(2);
      try {
    	  postMapper.getListBySearchWithPaging(1, cri).forEach(post->{System.out.println(post);});
         
      }catch(Exception e) {
         e.printStackTrace();
      }
   }

}