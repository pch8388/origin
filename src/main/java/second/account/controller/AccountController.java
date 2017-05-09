package second.account.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import second.account.dto.AccountDTO;
import second.account.service.AccountService;
import second.sample.user.UserVO;

@Controller
@RequestMapping("/account")
public class AccountController {
	private Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@Resource(name="accountService")
	private AccountService service;
	
	@RequestMapping("/account_book")
	public void accountBook(@ModelAttribute("dto")AccountDTO dto,HttpSession session,Model model)throws Exception{
		UserVO vo = (UserVO)session.getAttribute("login");
		List<AccountDTO> list = service.accountList(vo);
		
		model.addAttribute("list", list);
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
	@RequestMapping("/account_chart")
	public void accountChart(@ModelAttribute("vo")UserVO vo) throws Exception{
		
	}
	
	@RequestMapping("/account_chart_draw")
	public ModelAndView account_chart_draw(@RequestParam("id")String id) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		
		UserVO vo = new UserVO();
		vo.setId(id);

		List<AccountDTO> list = service.accountListSum(vo);
		JSONObject ajaxObjCols1 = new JSONObject();
		JSONObject ajaxObjCols2 = new JSONObject();
		JSONArray ajaxArryCols = new JSONArray();
		JSONArray ajaxArryRows = new JSONArray();
		
		ajaxObjCols1.put("label", "분류");
		ajaxObjCols1.put("type", "string");
		ajaxObjCols2.put("label", "금액");
		ajaxObjCols2.put("type", "number");
		
		ajaxArryCols.add(ajaxObjCols1);
		ajaxArryCols.add(ajaxObjCols2);
		
		for(AccountDTO dto : list){
			JSONObject name = new JSONObject();
			name.put("v", dto.getClassification());
			JSONObject money = new JSONObject();
			money.put("v", dto.getMoney());
			JSONArray row = new JSONArray();
			row.add(name);
			row.add(money);
			JSONObject cell = new JSONObject();
			cell.put("c", row);
			ajaxArryRows.add(cell);
		}
		mv.addObject("rows", ajaxArryRows);
		mv.addObject("cols",ajaxArryCols);
		log.info(mv.toString());
		return mv;
	}
}
