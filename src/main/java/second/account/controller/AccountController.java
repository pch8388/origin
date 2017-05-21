package second.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
import second.account.dto.AccountIncomeVO;
import second.account.service.AccountService;
import second.sample.user.UserVO;

@Controller
@RequestMapping("/account")
public class AccountController {
	private Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@Resource(name="accountService")
	private AccountService service;
	
	@RequestMapping("/account_book")
	public void accountBook(@ModelAttribute("dto")AccountDTO dto,HttpSession session,Model model,AccountIncomeVO vo)throws Exception{
		UserVO uvo = (UserVO)session.getAttribute("login");
		
		String date = service.dateCal();
		vo.setIncome_date(date);
		vo.setId(uvo.getId());
		String monthIncome = service.monthIncome(vo);
		model.addAttribute("monthIncome",monthIncome);
		dto.setAccount_date(date);
		dto.setId(uvo.getId());
		AccountDTO dtoSum = service.monthSpend(dto);
		if(dtoSum!=null){
			model.addAttribute("dtoSum", dtoSum);
			int sub = Integer.parseInt(monthIncome) - dtoSum.getMoney();
			model.addAttribute("sub", sub);	
		}else{
			dtoSum = new AccountDTO();
			dtoSum.setCard("0");
			dtoSum.setCash("0");
			dtoSum.setMoney(0);
			model.addAttribute("dtoSum", dtoSum);
			int sub = Integer.parseInt(monthIncome);
			model.addAttribute("sub", sub);
		}
	}
	
	@RequestMapping("/account_save")
	public ModelAndView accountSave(AccountDTO dto,Model model) throws Exception{
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
		return new ModelAndView("jsonView");
	}
	
	@RequestMapping("/account_list")
	public ModelAndView accountList(HttpSession session,HttpServletRequest req) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		String strPageIndex = (String)req.getParameter("PAGE_INDEX");
		String strPageRow = (String)req.getParameter("PAGE_ROW");
		int nPageIndex = 0;
		int nPageRow = 20;
		
		if(StringUtils.isEmpty(strPageIndex) == false){
			nPageIndex = Integer.parseInt(strPageIndex) - 1;
		}
		if(StringUtils.isEmpty(strPageRow) == false){
			nPageRow = Integer.parseInt(strPageRow);
		} 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageStart", (nPageIndex * nPageRow) + 1);
		map.put("perPageNum",(nPageIndex * nPageRow) + nPageRow);
		
		UserVO vo = (UserVO)session.getAttribute("login");
		map.put("id", vo.getId());
		
		List<AccountDTO> list = service.accountList(map);
		
		mav.addObject("list", list);
		if(list.size() > 0){
			mav.addObject("TOTAL", list.get(0).getTOTAL_COUNT());
		}else{
			mav.addObject("TOTAL", 0);
		}
		
		return mav;
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
	
	@RequestMapping("/account_income")
	public void accountIncome(@ModelAttribute("vo")AccountIncomeVO vo)throws Exception{
		
	}
	
	@RequestMapping("/account_income_save")
	public String accountIncomeSave(AccountIncomeVO vo)throws Exception{
		if(vo.getSalary()==null || vo.getSalary()==""){
			vo.setSalary("0");
		}
		if(vo.getIncome()==null || vo.getIncome()==""){
			vo.setIncome("0");
		}
		service.accountIncomeSave(vo);
		return "redirect:/account/account_income";
	}
	
	@RequestMapping("/account_income_list")
	public String accountIncomeList(AccountIncomeVO vo,Model model) throws Exception{
		
		List<AccountIncomeVO> list = service.accountIncomeList(vo);
		
		model.addAttribute("list", list);
		return "/account/account_income_list";
	}
	
	@RequestMapping("/account_delete")
	public ModelAndView accountDelete(@RequestParam("checkBoxValues[]")List<String> arrayParams)throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		log.info("arrayparams : "+arrayParams);
		service.accountDelete(arrayParams);
		mav.addObject(true);
		return mav;
	}
}
