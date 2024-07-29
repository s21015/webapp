package com.example.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webapp.entity.Account;

public interface AccountsRepository extends JpaRepository<Account, String>{

}
