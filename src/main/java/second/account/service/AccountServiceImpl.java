package second.account.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
	public List<AccountDTO> accountList(UserVO vo) throws Exception{
		return dao.accountList(vo);
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

}
