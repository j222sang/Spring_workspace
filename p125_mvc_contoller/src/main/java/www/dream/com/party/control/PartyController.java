package www.dream.com.party.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import www.dream.com.party.model.ContactPoint;
import www.dream.com.party.model.ContactPointType;
import www.dream.com.party.model.Party;

@Controller // Servlet Component로 Container에 담기기 위해서는 우리가 어디를 더 보고 확인해야 하나요?
//Servlet.xml을 확인합시다
@RequestMapping("/party/*")
public class PartyController {
	/**
	 * party/registerParty를 호출하면 회원가입화면 열기
	 * 
	 */
	@GetMapping(value="registerParty")
	public void openRegisterPartyView() {
		System.out.println("openRegisterPartyView");
	}
	
	/**
	 * Master-Detail 관계...
	 * party/createParty를 Post 방식으로 부를 때 작동
	 */
	@PostMapping(value = "createParty")
	public String createParty(Party obj, Model model) {
		System.out.println(obj);
		obj.setId(55);
		//newbie : 자바에서 변수이름
		model.addAttribute("newbie", obj);
		return "party/confirmParty";
	}
	
	/**
	 * ModelAndView는 Ajax에서 주로 사용함
	 */
	@PostMapping(value = "createPartyByAjax")
	public ModelAndView createPartyByAjax(Party obj) {
		ModelAndView ret = new ModelAndView("party/confirmParty");
		System.out.println(obj);
		obj.setId(55);
		//newbie : 자바에서 변수이름
		ret.addObject("newbie", obj);
		return ret;
	}
	
	@PostMapping(value = "createPartyByDefault")
	public String createPartyByDefault(Party obj, @ModelAttribute("nameOfM") int nameOfM) {
		System.out.println(obj);
		System.out.println(nameOfM);
		obj.setId(56);
		return "party/confirmParty";
	}

	@PostMapping(value = "createPartyByRedirect")
	public String createPartyByRedirect(Party obj, Model model, RedirectAttributes rttr) {
		System.out.println(obj);
		obj.setId(56);
		rttr.addAttribute("newbie", 456);
		return "redirect:/party/redirectedParty";
	}
	
	// redirect는 Get방식으로 재요청 해 줌
	@GetMapping(value = "redirectedParty")
	public String confirmPartyRedirect(@RequestParam("newbie") int newbie, Model model) {
		Party obj = new Party();
		System.out.println(newbie);
		obj.setId(57);
		model.addAttribute("newbie", obj);
		model.addAttribute("aaa", 32);
		return "/party/confirmParty";
	}
	
	/*
	 * JSON 메세지 출력
	 * AJax RestAPI
	 */
	@GetMapping(value = "getJson")
	public @ResponseBody Party getJsonOfParty() {
		Party obj = new Party();
		obj.setName("홍길동");
		obj.setId(57);
		
		ContactPoint cp = new ContactPoint();
		cp.setContactPointType(ContactPointType.phoneNum);
		cp.setValue("010-9007-6293");
		obj.addCP(cp);
		
		cp = new ContactPoint();
		cp.setContactPointType(ContactPointType.addr);
		cp.setValue("한양");
		obj.addCP(cp);
		return obj;
	}
}