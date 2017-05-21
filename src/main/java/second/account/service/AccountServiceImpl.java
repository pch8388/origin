package second.account.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import second.account.dao.AccountDAO;
import second.account.dto.AccountDTO;
import second.account.dto.AccountIncomeVO;
import second.sample.user.UserVO;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Resource(name="accountDAO")
	private AccountDAO dao;

	@Override
	public void accountSave(AccountDTO dto) throws Exception {
		dao.accountSave(dto);
	}

	@Override
	public List<AccountDTO> accountList(Map<String,Object> map) throws Exception{
		return dao.accountList(map);
	}

	@Override
	public List<AccountDTO> accountListSum(UserVO vo) throws Exception {
		return dao.accountListSum(vo);
	}

	@Override
	public void accountIncomeSave(AccountIncomeVO vo) throws Exception {
		dao.accountIncomeSave(vo);
	}

	@Override
	public List<AccountIncomeVO> accountIncomeList(AccountIncomeVO vo) throws Exception {
		return dao.accountIncomeList(vo);
	}

	@Override
	public String dateCal() throws Exception {
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-MM")) + "%";
		return date;
	}

	@Override
	public String monthIncome(AccountIncomeVO vo) throws Exception {
		return dao.monthIncome(vo);
	}

	@Override
	public AccountDTO monthSpend(AccountDTO dto) throws Exception {
		return dao.monthSpend(dto);
	}

	@Override
	public void accountDelete(List<String> arrayParams) throws Exception {
		Iterator<String> it = arrayParams.iterator();
		
		while(it.hasNext()){
			String checkBoxValues = (String)it.next();
			dao.accountDelete(checkBoxValues);
		}
	}

}
