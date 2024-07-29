package com.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.webapp.service.ThreadsService;

@RestController
public class DataContorller {

	@Autowired
	private ThreadsService threadsService;
	
	@GetMapping("/api/getLatestData/{id}")
    public ModelAndView getLatestData(@PathVariable int id) {
		
		ModelAndView mav = new ModelAndView("threads/threads :: threads");
		mav.addObject("threads", threadsService.findByTitleThreads(id));
    	
        // 最新のデータを取得して返すロジックを実装
        // ここでは簡単な例として、リポジトリから全てのToDoを取得して表示
        return mav;
    }
}
