package com.example.webapp.service;

import java.util.List;

import com.example.webapp.entity.ToDo;

/**
 * ToDo:サービス
 */
public interface ToDoService {
	/**
	 * 全「すること」を検索する
	 * @return
	 */
	List<ToDo> findAllToDo(String username);
	
	/**
	 * 指定されたIDの「すること」を検索する
	 * @param id
	 * @return
	 */
	ToDo findByIdToDo(Integer id);
	
	/**
	 * 「すること」を新規登録する
	 * @param toDo
	 */
	void insertToDo(ToDo toDo);
	
	/**
	 * 「すること」を更新する
	 * @param toDo
	 */
	void updateToDo(ToDo toDo);
	
	/**
	 * 指定されたIDの「すること」を削除する
	 * @param id
	 */
	void deleteToDo(Integer id);
}
