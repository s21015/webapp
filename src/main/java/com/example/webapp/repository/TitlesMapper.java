package com.example.webapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.webapp.entity.Titles;

@Mapper
public interface TitlesMapper {
	List<Titles> selectAll();
	List<Titles> selectById(@Param("id") Integer id);
	void insert(Titles titles);
	void update();
	void delete(@Param("id") int id);
}
