package second.account.service;

import java.util.List;
import java.util.Map;

import second.account.dto.AccountDTO;
import second.account.dto.AccountIncomeVO;
import second.sample.user.UserVO;

public interface AccountService {

	public void accountSave(AccountDTO dto) throws Exception;

	public List<AccountDTO> accountList(Map<String,Object> map)throws Exception;

	public List<AccountDTO> accountListSum(UserVO vo) throws Exception;

	public void accountIncomeSave(AccountIncomeVO vo) throws Exception;

	public List<AccountIncomeVO> accountIncomeList(AccountIncomeVO vo)throws Exception;

	public String dateCal() throws Exception;

	public String monthIncome(AccountIncomeVO vo) throws Exception;

	public AccountDTO monthSpend(AccountDTO dto) throws Exception;

}
