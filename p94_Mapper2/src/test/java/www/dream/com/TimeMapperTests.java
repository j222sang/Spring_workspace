package www.dream.com;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//해당 위치에 있는 데이터를 바탕으로 데이터를 만들어라
@ContextConfiguration("file:src\\main\\webapp\\WEB-INF\\spring\\root-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TimeMapperTests {

	@Autowired
	private TimeMapper timeMapper;

	@Test
	public void testByAnno() {
		assertNotNull(timeMapper);
		System.out.println(timeMapper.getTime());
	}

	@Test
	public void testByXML() {
		assertNotNull(timeMapper);
		System.out.println(timeMapper.getTimeByXML() + "xml");
	}
}
