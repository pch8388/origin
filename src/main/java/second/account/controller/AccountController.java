package second.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import second.sample.dto.LoginDTO;

@Controller
@RequestMapping("/account")
public class AccountController {

	@RequestMapping("/account_book")
	public void accountBook(@ModelAttribute("dto")LoginDTO dto)throws Exception{
		
	}
}
