package com.example.webapp.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.webapp.entity.Account;

public interface AccountService {
	
	List<Account> findAllAccounts();
	
	Account findByNameAccount(@Param("username") String username);
	
	List<Account> findByRoleUserAccount();
	
	void registAccount(Account account);
	
	void deleteAccount(@Param("username") String username);
}
