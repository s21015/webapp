package com.example.webapp.form;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitlesForm {
	private int id;
	
	@NotBlank(message = "タイトルを入力してください")
	private String title;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
