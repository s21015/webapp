package com.example.webapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.webapp.entity.Account;

@Mapper
public interface AccountMapper {
	
	List<Account> selectAll();
	
	Account selectByName(@Param("username") String username);
	
	List<Account> selectByRoleUser();
	
	void regist(Account account);
	
	void delete(@Param("username") String username);
}
