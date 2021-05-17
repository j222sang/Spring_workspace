package www.dream.com.di_sample;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//Junit이 () 안에 들어있는 객체를 읽어서 테스트를 해준다
@RunWith(SpringJUnit4ClassRunner.class)
//해당 위치에 있는 데이터를 바탕으로 데이터를 만들어라
@ContextConfiguration("file:src\\main\\webapp\\WEB-INF\\spring\\root-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDI {
	// 자동으로 객체를 넣어라
	@Autowired
	private Restaurant restaurant;
	
	@Autowired
	private Hotel hotel;
	
	@Test
	public void test() {
		// context에 객체가 만들어졌을때 계속 값이 유지되나? 아니면 
		restaurant.getChef().setName("홍길동");
		//객체가 null이 아님을 보장
		assertNotNull(restaurant);
		System.out.println(restaurant.getChef());
	}
	
	@Test
	public void testHotel() {
		//객체가 null이 아님을 보장
		assertNotNull(hotel);
		System.out.println(hotel.getChef());
	}

}
