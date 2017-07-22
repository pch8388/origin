package second.account.dao;

import java.util.List;
import java.util.Map;

import second.account.dto.AccountDTO;
import second.account.dto.AccountIncomeVO;

public interface AccountDAO {

	public void accountSave(AccountDTO dto) throws Exception;

	public List<AccountDTO> accountList(Map<String,Object> map) throws Exception;

	public List<AccountDTO> accountListSum(AccountDTO dto) throws Exception;

	public void accountIncomeSave(AccountIncomeVO vo) throws Exception;

	public List<AccountIncomeVO> accountIncomeList(Map<String,Object> map)throws Exception;

	public String monthIncome(AccountIncomeVO vo) throws Exception;

	public AccountDTO monthSpend(AccountDTO dto) throws Exception;

	public void accountDelete(String checkBoxValues) throws Exception;

	public void incomeDelete(String checkBoxValues) throws Exception;

}
