package www.dream.com.party.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import www.dream.com.party.model.Party;

@Controller // Servlet Component로 Container에 담기기 위해서는 우리가 어디를 더 보고 확인해야 하나요?
//Servlet.xml을 확인합시다
@RequestMapping("/party/*")
public class PartyController {

   /**
    * party/registerParty를 호출하면 회원가입화면 열기
    * 
    */
   @GetMapping("/registerParty")
   public void openRegisterPartyView() {
      System.out.println("openRegisterPartyView");
   }
   
   /**
    * party/md를 Get 방식으로 부를때 인자와 객체 속성을 이름과함께 짝지어 객체생성
    * @param obj
    */
   @GetMapping(value = "md")
   public void createParty(Party obj) {
      System.out.println(obj);
   }

}