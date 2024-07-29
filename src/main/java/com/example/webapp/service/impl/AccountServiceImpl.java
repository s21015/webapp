package com.example.webapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.Account;
import com.example.webapp.repository.AccountMapper;
import com.example.webapp.service.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
	
	private final AccountMapper accountMapper;

	@Override
	public List<Account> findAllAccounts() {
		return accountMapper.selectAll();
	}
	
	@Override
	public Account findByNameAccount(String username) {
		return accountMapper.selectByName(username);
	}
	
	@Override
	public List<Account> findByRoleUserAccount() {
		return accountMapper.selectByRoleUser();
	}
	
	@Override
	public void registAccount(Account account) {
		accountMapper.regist(account);;
	}

	@Override
	public void deleteAccount(String username) {
		accountMapper.delete(username);
	}
}
