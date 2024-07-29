package com.example.webapp.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MakeHashController {
	
//	@Autowired
//	PasswordEncoder encoder;
	
	public String passwordEncoder(String pas) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		// パスワードをハッシュ化
		String encodePassword = encoder.encode(pas);
		
		// ハッシュ化したパスワードを返す
		return encodePassword;
	}
}
