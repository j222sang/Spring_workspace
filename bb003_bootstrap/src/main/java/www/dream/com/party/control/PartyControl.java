package www.dream.com.party.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import www.dream.com.party.service.PartyService;
//servlet-context.xml에 등록해줘야함
@Controller
@RequestMapping("/party/*") // 현재클래스의 모든 메소드들의 기본적인 URL 경로가 된다
public class PartyControl {
	@Autowired
	private PartyService partyService;
	
	@GetMapping(value = "list")
	public void getList(Model model) {
		model.addAttribute("listParty", partyService.getList());
	}
}
