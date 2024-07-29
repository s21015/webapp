package com.example.webapp.helper;

import com.example.webapp.entity.Titles;
import com.example.webapp.form.TitlesForm;

public class TitlesHelper {

	public static Titles convertTitles(TitlesForm form) {
		Titles titles = new Titles();
		titles.setId(form.getId());
		titles.setTitle(form.getTitle());
		titles.setCreatedAt(form.getCreatedAt());
		titles.setUpdatedAt(form.getUpdatedAt());
		return titles;
	}
	
	public static TitlesForm convertTitlesForm(Titles titles) {
		TitlesForm form = new TitlesForm();
		form.setId(titles.getId());
		form.setTitle(titles.getTitle());
		form.setCreatedAt(titles.getCreatedAt());
		form.setUpdatedAt(titles.getUpdatedAt());
		return form;
	}
}
