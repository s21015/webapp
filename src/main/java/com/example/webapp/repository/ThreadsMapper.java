package com.example.webapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.webapp.entity.Threads;

@Mapper
public interface ThreadsMapper {
	List<Threads> selectAll();
	List<Threads> selectByTitle(@Param("titleId") int titleId);
	void insert(Threads threads);
	void delete(@Param("id") int id);
}
