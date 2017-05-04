package second.account.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import second.account.dto.AccountDTO;
import second.account.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Resource(name="accountService")
	private AccountService service;
	
	@RequestMapping("/account_book")
	public void accountBook(@ModelAttribute("dto")AccountDTO dto)throws Exception{
		
	}
	
	@RequestMapping("/account_save")
	public String accountSave(AccountDTO dto,Model model) throws Exception{
		service.accountSave(dto);
		return "redirect:/account/account_book";
	}
}
