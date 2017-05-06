package second.account.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import second.account.dto.AccountDTO;
import second.account.service.AccountService;
import second.sample.user.UserVO;

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
		if(dto.getCash()==null || dto.getCash()==""){
			dto.setCash("0");
		}
		if(dto.getCard()==null || dto.getCard()==""){
			dto.setCard("0");
		}
		if(dto.getMemo()==null || dto.getMemo()==""){
			dto.setMemo(" ");
		}
		service.accountSave(dto);
		return "redirect:/account/account_book";
	}
	
	@RequestMapping("/account_list")
	public void accountList(HttpSession session,Model model) throws Exception{
		UserVO vo = (UserVO)session.getAttribute("login");
		List<AccountDTO> list = service.accountList(vo);
		
		model.addAttribute("list", list);
	}
}
