package com.example.webapp.service;

import java.util.List;

import com.example.webapp.entity.Titles;

public interface TitlesService {
	List<Titles> findAllTitles();
	List<Titles> findByIdTitles(Integer id);
	void insertTilte(Titles titles);
	void updateTitle();
	void deleteTitle(int id);
}
