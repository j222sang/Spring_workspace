package www.dream.com.restStudy;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import www.dream.com.bulletinBoard.model.PostVO;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //브라우저 역할을 대신함
@ContextConfiguration({"file:src\\main\\webapp\\WEB-INF\\spring\\root-context.xml",
	"file:src\\main\\webapp\\WEB-INF\\spring\\appServlet\\servlet-context.xml"})
public class SampleControllerTest {
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	@Before //준비 단계
	public void setup() {
		//Mock 가상의, 가짜, 대행자
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void test() {
		PostVO post = new PostVO();
		post.setTitle("Rest Controller Test");
		post.setContent("잘되어야 할텐데");
		String jsonOfPost = new Gson().toJson(post);
		try {
			//get 방법으로 web Server에게 /party/list 이 url로 요청을 보낸다.
			ResultActions ra = mockMvc.perform(
					MockMvcRequestBuilders.post("/sample/registerPost/3").contentType(MediaType.APPLICATION_JSON)
					.content(jsonOfPost));
			ra.andExpect(status().is(200));
			MvcResult mvcResult = ra.andReturn();
			mvcResult.getAsyncResult();
		} catch (Exception e) {		
			e.printStackTrace();
		}
	}

}