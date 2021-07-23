package my.jes.web.service;

import org.springframework.stereotype.Service;

import my.jes.web.util.APIExamFace;
import my.jes.web.util.ChatbotProc;

@Service
public class AiService {
	//여러개의 API 서비스를 거칠 경우 여기서 처리
	public String faceRecognize(String fileName) {
		return APIExamFace.faceRecognize(fileName);
	}

	public String coffeeOrder() {
		return ChatbotProc.coffeeOrder("커피주세요");
	}
	
}
