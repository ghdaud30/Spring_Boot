package edu.pnu.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	@GetMapping("/login")
	public void login() {}
	@GetMapping("/loginSuccess")
	public void loginSuccess() {}
	@GetMapping("/accessDenied")
	public void accessDenied() {}
	
	@GetMapping("/auth")
	public @ResponseBody String auth(@AuthenticationPrincipal User user) {
		if(user == null) {
			System.out.println("User is null");
			return null;
		}
		return user.toString();
	}
	
	// 로그인 세션 정보 확인용 URL
	@GetMapping("/oauth")
	public @ResponseBody String auth(@AuthenticationPrincipal OAuth2User user) {
	if (user == null) return "OAuth2:null";
	// 자동 회원가입을 한다면 이용할 정보 확인
	System.out.println("attributes:" + user.getAttributes());
	return "OAuth2:" + user;
	}

}
