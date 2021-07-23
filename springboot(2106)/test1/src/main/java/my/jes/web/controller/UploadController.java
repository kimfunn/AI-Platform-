package my.jes.web.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import my.jes.web.service.AiService;

@Controller
public class UploadController {
	
	@Autowired
	AiService aiService;
	
	@PostMapping("coffeeOrder")
	@ResponseBody	//ajax 요청($.post) 시 꼭 이거 쓰기
	public String coffeeOrder() {
		String returnMsg = aiService.coffeeOrder();
		return returnMsg;
	}
	
	@PostMapping("faceUpload")
	public ModelAndView faceUpload(@RequestParam("face") MultipartFile file) {
		System.out.println(file);
		ModelAndView mav = new ModelAndView("ok");
		try {
			String fileName="c:\\temp\\"+file.getOriginalFilename();
			file.transferTo(new File(fileName));
			String emotion = aiService.faceRecognize(fileName);
			
			mav.addObject("emotion",emotion);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav; // RestController ,ResponseBody 안붙이면 .jsp로 이동
	}
	
	@GetMapping("test")
	public String test() {
		
		return "ok";
	}
	
}
