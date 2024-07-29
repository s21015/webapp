package com.example.webapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.Titles;
import com.example.webapp.repository.TitlesMapper;
import com.example.webapp.service.TitlesService;
import com.example.webapp.websocket.MyWebSocketHandler;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TitlesServiceImpl implements TitlesService{

	private final TitlesMapper mapper;
	private final MyWebSocketHandler myWebSocketHandler;
	
	@Override
	public List<Titles> findAllTitles() {
		return mapper.selectAll();
	}

	@Override
	public List<Titles> findByIdTitles(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.selectById(id);
	}

	@Override
	public void insertTilte(Titles titles) {
		mapper.insert(titles);
		try {
            myWebSocketHandler.sendMessage("update");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void updateTitle() {
		mapper.update();
	}

	@Override
	public void deleteTitle(int id) {
		mapper.delete(id);
		try {
            myWebSocketHandler.sendMessage("update");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
