package com.example.webapp.form;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadsForm {
	private int id;
	private int titleId;
	
	@NotBlank(message = "入力してください")
	private String thread;
	private String username;
	private LocalDateTime createdAt;
}
