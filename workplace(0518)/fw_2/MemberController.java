package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//인식안되는 이유 (스피링부트에서는 구조가 굉장히 중요함)
// java 소스코드 들어가는데 서브 패키지들이 전부 com.myboot에 들어가야함 근데 독립적으로 만들어 놓으면 하나도 인식하지 못함
// java 소스들은 정한 패키지명 안에 들어가야하는데 static 은 정적인 파일
//templates는 view 와 같은 타임리프와 같은 경우들이 들어가야함 (안쓰면 쓸일없음)
@Controller
public class MemberController {
	
	@ResponseBody
	@RequestMapping("/memberInsert")
	public String memberInsert(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("memberInsert호출됨");
		return"회원가입 ok";
	}
}
