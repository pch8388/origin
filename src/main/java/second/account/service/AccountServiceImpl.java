package second.account.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import second.account.dao.AccountDAO;
import second.account.dto.AccountDTO;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Resource(name="accountDAO")
	private AccountDAO dao;

	@Override
	public void accountSave(AccountDTO dto) throws Exception {
		dao.accountSave(dto);
	}
}
