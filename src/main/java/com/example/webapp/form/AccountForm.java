package com.example.webapp.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountForm {
	
	@NotBlank(message = "必須項目です")
	private String username;
	@NotBlank(message = "必須項目です")
	private String password;
	private String role;
}
