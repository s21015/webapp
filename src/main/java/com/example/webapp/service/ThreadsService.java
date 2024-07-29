package com.example.webapp.service;

import java.util.List;

import com.example.webapp.entity.Threads;

public interface ThreadsService {
	List<Threads> findAllThreads();
	List<Threads> findByTitleThreads(int titleId);
	void insert(Threads threads);
	void delete(int id);
}
