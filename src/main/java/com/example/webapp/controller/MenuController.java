package com.example.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webapp.entity.Account;
import com.example.webapp.form.AccountForm;
import com.example.webapp.helper.AccountHelper;
import com.example.webapp.service.AccountService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MenuController {
	
	private final AccountService accountService;
	
	@GetMapping
	public String showMenu() {
		// temlatesフォルダ配下のmenu.htmlに遷移
		return "menu";
	}
	
	@GetMapping("/user-edit")
	public String newAccount(@ModelAttribute AccountForm form) {
		return "accounts/regist";
	}
	
	@PostMapping("/regist")
	public String regist(@Validated AccountForm form, BindingResult bindingResult, RedirectAttributes attributes) {
		
		if(bindingResult.hasErrors()) {
			return "accounts/regist";
		}
		
		Account existAccount = accountService.findByNameAccount(form.getUsername());
		
		if (existAccount == null) {
			Account account = AccountHelper.convertAccount(form);
			
			MakeHashController makeHashController = new MakeHashController();
			account.setPassword(makeHashController.passwordEncoder(account.getPassword()));
			
			accountService.registAccount(account);
			attributes.addFlashAttribute("message", "ユーザーが登録されました");
			
			return "redirect:/";
		} else {
			attributes.addFlashAttribute("errorMessage", "このユーザー名は登録されています");
			return "redirect:/user-edit";
		}
	}
	
	@GetMapping("/user-delete")
	public String deleteSelect(Model model) {
		model.addAttribute("accounts", accountService.findByRoleUserAccount());
		return "accounts/delete";
	}
	
	@GetMapping("/user-delete/{username}")
	public String delete(@PathVariable String username, RedirectAttributes attributes) {
		accountService.deleteAccount(username);
		attributes.addFlashAttribute("message", "ユーザーが削除されました");
		return "redirect:/";
	}
}
