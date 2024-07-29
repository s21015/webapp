package com.example.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.Account;
import com.example.webapp.repository.AccountsRepository;

@Component
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private AccountsRepository accountsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Account account = accountsRepository.findById(username)
				.orElseThrow(() -> {
					System.out.println("ユーザー:" + username + "が見つかりません。");
					throw new UsernameNotFoundException(username);
				});
		
		return User.withUsername(
				account.getUsername())
				.password(account.getPassword())
				.roles(account.getRole())
				.build();
	}
}
