package com.example.webapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.Threads;
import com.example.webapp.repository.ThreadsMapper;
import com.example.webapp.service.ThreadsService;
import com.example.webapp.websocket.MyWebSocketHandler;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ThreadsServiceImpl implements ThreadsService {

	private final ThreadsMapper mapper;
	private final MyWebSocketHandler myWebSocketHandler;

	@Override
	public List<Threads> findAllThreads() {
		return mapper.selectAll();
	}

	@Override
	public List<Threads> findByTitleThreads(int titleId) {
		return mapper.selectByTitle(titleId);
	}

	@Override
	public void insert(Threads threads) {
		mapper.insert(threads);
		try {
			myWebSocketHandler.sendMessage("update");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		mapper.delete(id);
		try {
			myWebSocketHandler.sendMessage("update");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
