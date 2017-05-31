package second.account.service;

import java.util.List;
import java.util.Map;

import second.account.dto.AccountDTO;
import second.account.dto.AccountIncomeVO;

public interface AccountService {

	public void accountSave(AccountDTO dto) throws Exception;

	public List<AccountDTO> accountList(Map<String,Object> map)throws Exception;

	public List<AccountDTO> accountListSum(AccountDTO dto) throws Exception;

	public void accountIncomeSave(AccountIncomeVO vo) throws Exception;

	public List<AccountIncomeVO> accountIncomeList(Map<String,Object> map)throws Exception;

	public String monthIncome(AccountIncomeVO vo) throws Exception;

	public AccountDTO monthSpend(AccountDTO dto) throws Exception;

	public void accountDelete(List<String> arrayParams) throws Exception;

	public void incomeDelete(List<String> arrayParams) throws Exception;

}
