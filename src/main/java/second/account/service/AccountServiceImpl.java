package second.account.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import second.account.dao.AccountDAO;
import second.account.dto.AccountDTO;
import second.account.dto.AccountIncomeVO;

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
	public List<AccountDTO> accountListSum(AccountDTO dto) throws Exception {
		return dao.accountListSum(dto);
	}

	@Override
	public void accountIncomeSave(AccountIncomeVO vo) throws Exception {
		dao.accountIncomeSave(vo);
	}

	@Override
	public List<AccountIncomeVO> accountIncomeList(Map<String,Object> map) throws Exception {
		return dao.accountIncomeList(map);
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

	@Override
	public void incomeDelete(List<String> arrayParams) throws Exception {
		Iterator<String> it = arrayParams.iterator();
		
		while(it.hasNext()){
			String checkBoxValues = (String)it.next();
			dao.incomeDelete(checkBoxValues);
		}
	}

}
