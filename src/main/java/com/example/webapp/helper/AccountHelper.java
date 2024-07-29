package com.example.webapp.helper;

import com.example.webapp.entity.Account;
import com.example.webapp.form.AccountForm;

public class AccountHelper {

	public static Account convertAccount(AccountForm form) {
		Account account = new Account();
		account.setUsername(form.getUsername());
		account.setPassword(form.getPassword());
		account.setRole(form.getRole());
		return account;
	}
	
	public static AccountForm convertAccountForm(Account account) {
		AccountForm accountForm = new AccountForm();
		accountForm.setUsername(account.getUsername());
		accountForm.setPassword(account.getPassword());
		accountForm.setRole(account.getRole());
		return accountForm;
	}
}
