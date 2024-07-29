package com.example.webapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.webapp.entity.ToDo;

/**
 * ToDo:リポジトリ
 */
@Mapper
public interface ToDoMapper {
	/**
	 * 全ての「すること」を取得する
	 * @return 
	 */
	List<ToDo> selectAll(@Param("username") String username);
	
	/**
	 * 指定されたIDに対応する「すること」を取得する
	 * @param id
	 * @return
	 */
	ToDo selectById(@Param("id") Integer id);
	
	/**
	 * 「すること」を登録する
	 * @param toDo
	 */
	void insert(ToDo toDo);
	
	/**
	 * 「すること」を更新する
	 * @param toDo
	 */
	void update(ToDo toDo);
	
	/**
	 * 指定されたIDの「すること」を削除する
	 * @param id
	 */
	void delete(@Param("id") Integer id);
}
